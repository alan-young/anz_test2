FROM openjdk:12-jdk-alpine
ARG JAR_FILE=build/libs/simpleapplication.jar
ARG VERSION
ARG COMMIT_SHA
COPY ${JAR_FILE} simpleapplication.jar
ENV VERSION=${VERSION} COMMIT_SHA=${COMMIT_SHA}
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/simpleapplication.jar"]