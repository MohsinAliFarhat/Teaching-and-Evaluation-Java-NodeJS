const express = require("express");
const router  =express.Router();
const user = require("../models/user")
const checkHashPassword = require("../passwordHashing");

router.post("/",(req,res)=>{

var post_data = req.body;
var email = post_data.email;
var userPassword = post_data.password;
//Check If email exists
  user
    .find({ email: email })
    .countDocuments(function(err, number) {

      if (number == 0) {
        res.json({info:{msg:"Email doesn't exist!",email:"",name:""}});
        console.log("Email deosnt not exist");
        
      } else {
        //INSERT DATA
        user.findOne({ email: email }, function(
          err,
          user
        ) {
          var salt = user.salt; // GET SALT FROM USER
          var hashed_password = checkHashPassword(userPassword, salt)
            .passwordHash; // HASH PASSWORD WITH SALT
          var encrypted_password = user.password; // GET PASSWORD FROM USER

          if (hashed_password == encrypted_password) {
            
            res.json({info:{msg:"Login Success",email:email,name:user.name}});
            console.log("Login Success");
          } else {
            res.json({info:{msg:"Wrong Password",email:"",name:""}});
            console.log("Wrong Password");
          }
        });
      }
    });
})

module.exports = router;