const express = require('express');
const app = express();
const port = 3000;

const userData = (a, b) => ({
  try: a,
  goal: b,
});

const users = {}

function shuffle(array) {
  var currentIndex = array.length, temporaryValue, randomIndex;

  while (0 !== currentIndex) {
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex -= 1;

    temporaryValue = array[currentIndex];
    array[currentIndex] = array[randomIndex];
    array[randomIndex] = temporaryValue;
  }

  return array;
}

const template = [ '1', '2', '3', '4', '5', '6', '7', '8', '9'];

const makeJson = (judge, tried, correct, try_count) => ({
  "JUDGEMENT": judge,
  "TRIED_NUMBER": tried,
  "CORRECT": correct,
  "TRY_COUNT": try_count
});

app.get('/', (req, res) => res.send('Hello World'));

app.get('/baseball', (req, res) => {
  if(!req.query.user) {
    res.status(404).send('You are Not User!!');
  }
  
  const { query: { user, numbers } } = req;

  if(!numbers.match(/[1-9]{4}/g)) return res.status(404).send('You must ipnut 4 numbers as 1 to 9!');
  
  if(!users[user]) {
    users[user] = userData(1, shuffle(template).slice(0, 4));
  } else {
    users[user].try += 1;
  }

  const input = numbers.split('');

  let results = users[user].goal.map((item, index) => {
    if(item === input[index]) return 'S';
    else if(input.includes(item)) return 'B';
    else return 'O';
  }, []).sort();

  const str = results.reduce((acc, cur) => {
    if(!acc[cur]) {
      acc[cur] = 1;
    } else acc[cur] += 1;

    return acc;
  }, {});

  
  results = Object.keys(str).reduce((acc, cur) => `${str[cur]}${cur}` + acc, '');

  if(results === '4S') {
    const json = makeJson(results, numbers, true, users[user].try);
    users[user] = userData(0, shuffle(template).slice(0, 4));
    
    return res.status(200).json(json);
  } else if(users[user].try > 20) {
    const json = makeJson(results, numbers, false, users[user].try);
    users[user] = userData(0, shuffle(template).slice(0, 4));
    return res.status(400).json(json);
  } else return res.status(201).json(makeJson(results, numbers, 'true or false', users[user].try));
});

app.listen(port, () => console.log(`Running on port ${port}`));