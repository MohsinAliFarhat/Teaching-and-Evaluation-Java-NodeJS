const mongoose = require("mongoose");
const Schema = mongoose.Schema;

//Schema
const userSchema = new Schema({


   email: {
        type:String,
        required:true
     },
   password: {
        type:String,
        required:true
     },
   salt:{
        type: String,
        required:true
   },
   name:{
       type:String,
       required:true
   }

});
 
userModel = mongoose.model("user", userSchema);
module.exports = userModel;