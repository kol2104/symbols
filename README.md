# Symbols

## Description

Project to count symbols in input string

## Getting start

### Run service

1. Clone the repository

`git clone https://github.com/kol2104/symbols`

2. Change the directory

`cd symbols`

3. Run service

`./mvnw clean spring-boot:run`

Else you have installed maven you can run service by

`mvn clean spring-boot:run`

Also, you can run service using next commands:

`./mvnw clean package`

`java -jar ./target/symbols-0.0.1.jar`

Service launched!

### Next Step

Now service is available on `SYMBOLS_PORT` (or `8081` by default).

You can get list of symbols on `/parse` endpoint (`GET` request). The string to parse must be specified as a query parameter with `string` name.

The string must contain only `a-z` and `A-Z` symbols.

Example:

GET request `/parse?string=abcccacc` will return

    [
        {
            "c": 5
        },
        {
            "a": 2
        },
        {
            "b": 1
        }
    ]

## Environment variables

You can run service on your own port using next environment variable (using `8081` port when variable is not set):

    SYMBOLS_PORT

## Used technologies

1. Java 8
2. Spring Boot 2.7.15
