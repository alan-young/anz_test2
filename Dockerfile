FROM openjdk:12-jdk-alpine
ARG JAR_FILE=build/libs/simpleapplication.jar
ARG COMMIT_SHA
COPY ${JAR_FILE} simpleapplication.jar
COPY VERSION.txt VERSION.txt
ENV COMMIT_SHA=${COMMIT_SHA}
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/simpleapplication.jar"]