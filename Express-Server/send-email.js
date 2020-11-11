var nodemailer = require('nodemailer');
const express = require("express");
const router = express.Router();
const userModel = require("./models/user");

var transporter = nodemailer.createTransport({
  service: 'gmail',
  auth: {
    user: 'm.alifarhat114@gmail.com',
    pass: 'yourpassword'
  }
});

sendNewMail = (email,code)=>{

  var mailOptions = {
    from: 'm.alifarhat114@gmail.com',
    to: ''+email,
    subject: 'Quran App Password Recovery!',
    text: 'Here is your password recovery code: '+code
  };

transporter.sendMail(mailOptions, function(error, info){
  if (error) {
    console.log(error);
  } else {
    console.log('Email sent: ' + info.response);
  }
}); 
}

 router.post("/", (request, response, next) => {

  var post_data = request.body;

  var email = post_data.email;

  var code = post_data.code;

  userModel
  .find({ email: email })
  .count(function(err, number) {
    if (number == 0) 
    {

      response.json({info:{msg:"Email doesn't exist!",code:"0",name:""}});
      console.log("Email does not exist");

    } else {

      sendNewMail(email,code);
      response.json({info:{msg:"Email sent successfully!",code:"1",name:""}});

    }

 });
 });
 module.exports = router;
