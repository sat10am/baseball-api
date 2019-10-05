# Getting Start
---

## Start Server
---
```
$ docker-compose up -d
```

## Start Game
---
### Make a request
```
$ curl http://localhost:49160/baseball?user='your name'&numbers='your numbers'
```

### Response 
```
{"JUDGEMENT":"2O2B","TRIED_NUMBER":"1234","CORRECT":"true or false","TRY_COUNT":1}
```