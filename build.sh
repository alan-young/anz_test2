#!/bin/bash

docker run --rm -u gradle -v"$PWD":/home/gradle/project -w /home/gradle/project gradle:5.6.3-jdk12 gradle build

export APP=anz_test2
export COMMIT_SHA=$(git rev-parse HEAD)

if [[ -n "${PROJECT_ID}" ]]; then
  SHA_TAG="gcr.io/${PROJECT_ID}/${APP}:${COMMIT_SHA}"
  LATEST_TAG="gcr.io/${PROJECT_ID}/${APP}:latest"
else
  SHA_TAG="${APP}:${COMMIT_SHA}"
  LATEST_TAG="${APP}:latest"
fi

docker build --build-arg VERSION=$(cat VERSION.txt) --build-arg COMMIT_SHA=$COMMIT_SHA -t $SHA_TAG -t $LATEST_TAG .
