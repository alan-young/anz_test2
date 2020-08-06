#!/bin/bash

export COMMIT_SHA=$(git rev-parse HEAD)
export APP=anz_test2
export REPO=docker.io/namelessnameless/$APP

TAG="${APP}:${COMMIT_SHA}"

docker tag $TAG $REPO:${COMMIT_SHA}
docker push $REPO:${COMMIT_SHA}
docker tag $TAG $REPO:latest
docker push $REPO:latest

