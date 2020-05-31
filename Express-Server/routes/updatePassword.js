const userModel = require("../models/user");
const express = require("express");
const router = express.Router();

router.post("/", (request, response, next) => {

      var post_data = request.body;
      var plaint_password = post_data.pass;
      var hash_data = saltHashPassword(plaint_password);
      var password = hash_data.passwordHash; // SAVE PASSWORD HASH
      var salt = hash_data.salt; // save salt
      var email = post_data.email;
    
      //CHECK EXIST EMAIL
      userModel
        .find({ email: email })
        .count(function(err, number) {
          if (number == 0) {
            response.json("No Email already exists");
            console.log("No Email already exists");
          } else {
            //INSERT DATA
            userModel.updateOne({email:email},{$set:{password:password,salt:salt}}, function(error, res) {
              response.json("Update done!");
              console.log("Update Done!");
            });
          }
        })
    
     });

module.exports = router;