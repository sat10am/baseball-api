# Getting Start

## Start Server
```
$ docker-compose up -d
```

## Start Game

### Make a request
```
$ curl -i http://http://localhost:8080/baseball?id={고유값}&number={숫자4자리}
```

### Response 
```
HTTP/1.1 200 OK
Access-Control-Allow-Credentials: true
Access-Control-Allow-Headers: X-Requested-With, Content-Type, Accept, Origin, Authorization
Access-Control-Allow-Methods: GET, POST, PUT, PATCH, DELETE, OPTIONS
Access-Control-Allow-Origin:
Cache-Control: no-store, no-cache, must-revalidate, max-age=0
Cache-Control: post-check=0, pre-check=0
Connection: close
Content-Type: application/json
Date: Fri, 11 Oct 2019 16:46:38 GMT
Expires: Thu, 19 Nov 1981 08:52:00 GMT
Host: localhost:8080
Pragma: no-cache
Set-Cookie: PHPSESSID=47445ff875208312ebabf3c596d7dea1; path=/
X-Powered-By: PHP/7.3.10

{
    "data": {
        "CORRECT": false,
        "JUDGEMENT": "0S 0B",
        "TRIED_NUMBER": "9315",
        "TRY_COUNT": 1
    },
    "statusCode": 200
}

```