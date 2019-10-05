# baseball-api

```http

HTTP/2.0 GET /baseball?numbers=1234

# NOTE: 보통의 정답 맞추기 요청. 사용자의 세션, IP 등 자유로운 식별자를 체크해서 시도 횟수를 저장한다
# NOTE: 예를 들어 꼼수로, QueryString 에서 사용자 이름을 받아서 해도 좋음

RESPONSE 200 OK application/json

{
  "JUDGEMENT": "1B 3S",
  "TRIED_NUMBER": "1234",
  "CORRECT": true or false,
  "TRY_COUNT": 1
}

# NOTE: 20번 이상 정답을 맞추려고 하는 경우 아래 Response 를 반환하고, 새로운 번호를 생성한다

RESPONSE 400 BAD_REQUEST application/json
{
  "JUDGEMENT": "4B",
  "TRIED_NUMBER": "1234",
  "CORRECT": false,
  "TRY_COUNT": 21
}

# NOTE: 정답을 맞춘 경우, 아래 Response 를 반환하고, 새로운 번호를 생성한다

RESPONSE 201 CREATED application/json
{
  "JUDGEMENT": "4S",
  "TRIED_NUMBER": "1234",
  "CORRECT": true,
  "TRY_COUNT": 5
}
```
