# Getting Start

## Start Server
```
$ docker-compose up -d
```

## Start Game

### Make a request
```
$ curl -i http://localhost:49160/baseball?user='your name'&numbers='your numbers'
```

### Response 
```
HTTP/1.1 201 Created
X-Powered-By: Express
Content-Type: application/json; charset=utf-8
Content-Length: 82
ETag: W/"52-GQyxfS9zKmMbMjEDxSxJRQ8P1Y0"
Date: Sat, 05 Oct 2019 14:05:41 GMT
Connection: keep-alive

{"JUDGEMENT":"2O2B","TRIED_NUMBER":"1234","CORRECT":"true or false","TRY_COUNT":2}%
```