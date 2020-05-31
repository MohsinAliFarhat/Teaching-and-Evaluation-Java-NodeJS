const express  =require("express");
const router = express.Router();
const userModel = require("../models/user");
const checkHashPassword = require("../passwordHashing");
router.post("/",(req,res)=>{

          var post_data = req.body;
          var plaint_password = post_data.password;
          var hash_data = saltHashPassword(plaint_password);
          var password = hash_data.passwordHash; // SAVE PASSWORD HASH
          var salt = hash_data.salt; // save salt
          var name = post_data.name;
          var email = post_data.email;
    
    const newUser = new userModel({

        email: email,
        password: password,
        salt: salt,
        name: name

    })


      userModel
        .find({ email: email })
        .count(function(err, number) {
          if (number != 0) {
            res.json("Email already exists");
            console.log("Email already exists");
          } else {
            //INSERT DATA
            newUser.save().then(newUser=>res.json("Registraion done"));
              console.log("Registraion done");
          }
        });

});
module.exports = router;