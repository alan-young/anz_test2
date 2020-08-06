#!/bin/bash

export APP=anz_test2
export COMMIT_SHA=$(git rev-parse HEAD)

SHA_TAG="gcr.io/${PROJECT_ID}/${APP}:${COMMIT_SHA}"
LATEST_TAG="gcr.io/${PROJECT_ID}/${APP}:latest"

docker build --build-arg VERSION=$(cat VERSION.txt) --build-arg COMMIT_SHA=$COMMIT_SHA -t $SHA_TAG -t $LATEST_TAG .
