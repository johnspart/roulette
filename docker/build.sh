#!/bin/bash
echo "Building ..."
docker pull redis:latest
docker build --tag roulette-app:1.0 .
echo "Done."
