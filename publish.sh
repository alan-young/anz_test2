#!/bin/bash

COMMIT_SHA=$(git rev-parse HEAD)
APP=anz_test2
REPO=docker.io/namelessnameless/$APP

TAG="${APP}:${COMMIT_SHA}"

docker tag $TAG $REPO:${COMMIT_SHA}
docker push $REPO:${COMMIT_SHA}
docker tag $TAG $REPO:latest
docker push $REPO:latest

