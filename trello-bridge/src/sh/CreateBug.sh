#!/bin/bash
curl --location --request POST 'localhost:3000' \
--header 'Content-Type: application/json' \
--data-raw '{
    "type":"bug",
    "description":"Cockpit is not depressurizing correctly"
}'