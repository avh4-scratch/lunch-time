#!/bin/bash

function reset() {
psql template1 <<EOF
DROP DATABASE IF EXISTS $1;
CREATE DATABASE $1;
DROP USER IF EXISTS $1;
CREATE USER $1 WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE $1 TO $1;
EOF
}

reset lunch_time_integration_test
reset lunch_time_dev
