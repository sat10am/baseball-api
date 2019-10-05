# baseball-api

## TODO

- [ ] GET /baseball?number=1234 API 를 만드세요
- [ ] API 는 다음의 정해진 응답을 하도록 처리하세요
  ```http
  HTTP/1.1 200 OK
  Content-Length: 77
  Content-Type: application/json
  Date: Sat, 05 Oct 2019 09:23:17 GMT
  Server: WSGIServer/0.2 CPython/3.7.3
  X-Frame-Options: SAMEORIGIN

  {
    "CORRECT": false,  // 판정 결과
    "JUDGEMENT": "1B",  // 플레이어가 시도한 번호
    "TRIED_NUMBER": "1234",  // 정답 여부 - 맞으면 true, 틀리면 false
    "TRY_COUNT": 5  // 플레이어의 이번 게임 시도 횟수
  }
  ```
- [ ] 게임은 20번을 초과하여 플레이 할 수 없습니다
  -  다만 **20번이 넘어가면, 새 게임을 만드세요** (BAD_REQUEST 사용 안 함)
- [ ] `Response`의 `Content-Type` 은 `application-json` 으로 해주세요
- [ ] 플레이어의 session, cookie, remode_addr, query parameter 등 다양한 방법을 사용해, 게임 
기록을 유지해주세요
