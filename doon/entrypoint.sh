#!/bin/bash

# Run Migrate
cd /app

echo "Run Database Migration"
./baseball/manage.py migrate

echo "Run Server"
./baseball/manage.py runserver 0:8000
