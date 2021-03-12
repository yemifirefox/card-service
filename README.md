# card-service

This application is deployed on Heroku cloud platform
and can be accessed on the following endpoint
1. GET -> https://my-card-service.herokuapp.com/card-scheme/verify/{cardNumber}
2. GET -> https://my-card-service.herokuapp.com//card-scheme/stats?start=1&limit=3

These are some of the assumptions I made on this application
The first question doesn't specify where data are to be picked (i.e API call or Database).
GET -> https://my-card-service.herokuapp.com/card-scheme/verify/6011111111111117

{
    "success": true,
    "payload": {
        "scheme": "Discover",
        "type": "debit",
        "bank": "UBA"
    }
}
I was able to check the validity and get the scheme based on the Card Number but the type and 
bank paramaters value where hardcoded because there is no way I can get the bank name and type
unless I have to call an API or database or auto generate it.

Also Because of the time contstraint for the statistics, I decided to you use a static Map to serve as as 
my database to hold and update API hits. For optimization I could have use an independent cache database like Redis
to hold the value. So the data in map are lost each time the application is refreshed or restarted.
