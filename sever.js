var express = require('express');
var mysql = require('mysql');

var connection = mysql.createConnection({
    host : '192.168.8.1',
    user : 'jhe',
    password : 'jhe31240',
    database : 'misae_db'
});


var app = express();


connection.connect(function(err){
  if(!err) {  
    console.log("Database is connected ... \n\n");    
  } else {  
   console.log("Error connecting database ... \n\n");    
  }  
});  


app.get('/misae_db', function(req, res) {

   console.log("who get in here/misae_db");

   connection.query('SELECT * from weather_now', function (err, row, fields) {
   connection.end();
       if (!err) {
           //res.send(row);
           console.log("The solution is : ", row);
           res.json(row);
       }
       else
           console.log("Error");
   });
});



app.listen(3000, function() {

  console.log("Example app listening on port 3000!");

});