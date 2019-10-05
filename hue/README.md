session 기반으로 유저 판단

## start
> $ docker-compose up -d

8080포트 사용

## baseball 플레이

#### request

> http://localhost:8080/baseball?numbers=1487

#### response

```json
Status Code: 200
content-type: application/json;charset=UTF-8
date: Sat, 05 Oct 2019 12:41:58 GMT

{
    judgement: "1S 1B 2O",
    triedNumber: "1234", 
    correct: false, 
    tryCount: 2, 
}
```

```json
Status Code: 400 
Request URL: http://localhost:8080/baseball?numbers=12344
Request Method: GET

4개의 숫자를 입력하셔야 합니다.
```

## 정답 조회

#### request

> http://localhost:8080/baseball/result

#### response

```json
Request Method: GET
Status Code: 200 
Remote Address: [::1]:8080

2038

```