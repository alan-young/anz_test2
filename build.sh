#!/bin/bash

docker run --rm -u gradle -v"$PWD":/home/gradle/project -w /home/gradle/project gradle:5.6.3-jdk12 gradle build

if [ $? -ne 0 ] ; then exit ; fi

APP=anz_test2
COMMIT_SHA=$(git rev-parse HEAD)

SHA_TAG="${APP}:${COMMIT_SHA}"
LATEST_TAG="${APP}:latest"

docker build --build-arg COMMIT_SHA=$COMMIT_SHA -t $SHA_TAG -t $LATEST_TAG .
