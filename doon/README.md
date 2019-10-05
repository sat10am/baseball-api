# 🚀 Getting Start

## 📦 Install HTTP Client

```bash
$ brew install httpie
```

## 🏃‍♂️ Start Server

```bash
$ docker-compose up -d
```

## 🎮 Start Game

### Make a request

```bash
$ http GET http://localhost:9200 username==doon number==1234
```

### Response

```http
HTTP/1.1 200 OK
Content-Length: 77
Content-Type: application/json
Date: Sat, 05 Oct 2019 09:23:17 GMT
Server: WSGIServer/0.2 CPython/3.7.3
X-Frame-Options: SAMEORIGIN

{
    "CORRECT": false,
    "JUDGEMENT": "1B",
    "TRIED_NUMBER": "1234",
    "TRY_COUNT": 5
}
```
