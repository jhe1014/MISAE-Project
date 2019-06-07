const express = require('express');

const app = express();

let weather_condition = [

  {

    id: 1,
    condition: '맑음', 
    comment : '오늘은 산책하기 좋은 날',
    max_temp : 30,
    min_temp : 18
      
  }

]




//단순히 http://localhost:3000/users로 접근하면 users라는 객체를 json으로 response하라는 의미입니다.

app.get('/weather_condition', (req, res) => {

   console.log("who get in here/weather_condition");

   res.json(weather_condition)

});




app.listen(3000, () => {

  console.log("Example app listening on port 3000!");

});