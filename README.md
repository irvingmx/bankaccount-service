# bankaccount-service - REST Services

It is a REST Microservices Layer implementing the management of BankAccounts

## Github repository

https://github.com/irvingmx/bankaccount-service

## Heroku Deployed

As convinience for testing and consuming the services they are deployed on Heroku Cloud Service platform, they uri is 

https://irvingmx-bankaccount-service.herokuapp.com/

## Swagger UI API Documentation

This Api is documented vía Swagger you can find the API Description on the following url 

https://irvingmx-bankaccount-service.herokuapp.com/swagger-ui.html

## Resources exposed

* account-resource (get/post/put)
* credit-card-resource(get/post)
* customer-resource(get/post/put)
* notification-resource(put)
* score-resource(get)
* transaction-resource(get)

## Customer EMAIL mandatory

It's mandatory to fill email on Customer registration, the transaction notifications will be sent to this email address 

# Testing 

## Postman Collection

For testing purposes is provided a postman collection on root path of project named bankaccount-services.postman_collection.json  

## Dummy National documents for CreditCardScoreChecker

You can find the dummies national numbers with its score for testing executing GET verb of score resource they are stored in db

* 03185538S - 85
* 67855738R - 78
* 12345678A - 85
* 12345678B - 62
* 12345678C - 45
* 12345678D - 35
* 12345678E - 58
* 12345678F - 35
* 12345678Y - 45
* 12345678Z - 92

## Minimun SCORE for get a CREDIT CARD 

The minimun SCORE for getting a Credit Card is 50 it is configures on src/main/resources/application.properties
irvingmx.scoreChecker.limit=50

## Populated DB

For testing purposses they are dummies data on DB, you can find them in 

src/main/resources/data.sql

## CHANGELOG

irvingmx-0001 - BASELINE 

