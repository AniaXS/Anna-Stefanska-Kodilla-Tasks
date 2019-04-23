#!/usr/bin/env bash

if ./runcrud.sh; then
    google-chrome http://localhost:8080/crud/v1/task/getTasks
else
    echo "There were errors with runcrud"
fi