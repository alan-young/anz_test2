#!/bin/bash

kubectl --namespace=technical-test apply -f kubernetes
URL="http://"$(kubectl get -o jsonpath="{.status.loadBalancer.ingress[0].ip}"  --namespace=technical-test service anz-test2)":8080/version"
echo "Application is at: $URL"