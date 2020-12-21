#!/bin/bash
curl --location --request POST 'localhost:3000' \
--header 'Content-Type: application/json' \
--data-raw '{
    "type":"issue",
    "title":"Send message",
    "description":"Let pilots send messages to Central"
}'