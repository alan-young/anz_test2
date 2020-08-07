# ANZ Tech Test 2

I'm using Java/SpringBoot/Gradle to write the API service.

## CI pipeline

Gradle and its plugins handles most of the CI tasks. 
The following CI tasks are performed by Gradle:

- Unit testing
- Linting via Checkstyle
- Code quality checks via Spotbugs
- Unit testing coverage checks (needs to be at 100%) via Jacoco

All the above steps generate reports in ```build/reports```

Ideally I would have liked to have added the following to the CI process:
- something for detecting software supply chain issues - like OWASP Dependency Check 
- container scanning using something like Trivy

But they both take a fair amount of time to setup & test.
 
The gradle build process produces a 'fat jar' which is then deployed into a docker container.

### Running locally

Docker is required.

The ```build.sh``` script is used to build the containerised app, the image is tagged with ```anz_test2:<GIT COMMIT SHA>``` and ```anz_test2:latest```

### From Cloud Build

The ```cloudbuild.yaml``` file can be used as a trigger target to build the Docker image and store it in Google Container Repository.

The image will be tagged with ```gcr.io/$PROJECT_ID/anz_test2:<GIT COMMIT SHA>``` as well as ```gcr.io/$PROJECT_ID/anz_test2:latest```and pushed to your project's GCR repository.

## Publishing

###Local
The script ```publish.sh``` is used to publish the image to a container registry.  

## Deployment

###Local deployment

The container can be run via:

```
$ docker run -p8080:8080 anz_test2:latest 
```

### Kubernetes Deployment
Deployment into kubernetes is via: 
```
./kubernetes_deploy.sh
```
Kubernetes_deploy,sh assumes that there is a namespace called “technical-test”, if not create it via ```kubectl create namespace technical-test```

## Considerations

### Versioning 

- Semantic Versioning is used to manage versioning. The current version number of the app is stored in a VERSION.txt file.
- Container images are tagged with the current commit SHA1, the current commit SHA and version number are also created as environment variables in the image.

## Limitations and Risks

- The package dependencies are pinned, but they should also be pulled from a private repo like artifactory, currently they are being pulled from the public Maven repo.
- As mentioned above, a dependency scanner needs to be added to the CI process.
- The application runs in an embedded Tomcat Container. No lock down of it has been done.
- The default Spring Acutator endpoints /actuator/health (used for a kubernetes readiness probe) and /actuator/info are also turned on by default. Right now they don't leak anything sensitive (/health just returns a status and /info returns {}) but they should not be publically reachable.
- No error handling is included in the application - so it's not production ready.
- Even though Spotbugs is good for finding 'bugs' - it's not a dedicated security code scanner, something like Fortify or Coverity should also be considered.
- Alpine is used as a base image which already has a small attack surface. Something like distroless as a base image and compiling the app using GraalVM to a native binary would reduce the attack surface significantly.  
- The app container runs as the root user, this should be locked down to make it production ready.
- The Kubernetes deployment manifest ```kubernetes/deployment.yaml```has not had any best practice security applied to it i.e. no restrictions on running as privileged user or restrictions based on linux capabilities. This needs to be done as part of productionising it.

- Metrics will also need to be defined as part of productionising the app. 
- Integration testing has not been included in the CI pipeline, ideally the containerised app should be spun up and then the /version endpoint pinged as part of an integration test.
- Building with the standard Gradle image is slow as each time all dependencies are downloaded to the gradle cache; to speed the process up look at using a custom Gradle image with all the dependencies preloaded or turning the BuildKit version of docker on to take advantage of the ability to use a cache mount. 
- The ```publish.sh``` and ```build.sh``` could be combined into a Makefile so we're not duplicating variable definitions
- Because we are passing in the version information at build time, there could be a mismatch between the actual version and what the user sets. 

