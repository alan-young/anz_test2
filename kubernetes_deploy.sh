#!/bin/bash

# default image to deploy
IMAGE_TO_DEPLOY=namelessnameless/anz_test2:latest

# can be overriden by command line argument
if [ -n "$1" ]; then IMAGE_TO_DEPLOY=$1; fi

export IMAGE_TO_DEPLOY
echo "deploying image: "$IMAGE_TO_DEPLOY

cat kubernetes/deployment.yaml | sed  "s|{IMAGE}|$IMAGE_TO_DEPLOY|g" | kubectl --namespace=technical-test apply -f -
kubectl --namespace=technical-test apply -f kubernetes/service.yaml