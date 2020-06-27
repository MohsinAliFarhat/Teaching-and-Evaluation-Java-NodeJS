var express = require("express");
var router = express.Router();

var mongoose = require("mongoose");
mongoose.connect("mongodb://localhost:27017/QuranDb",{useNewUrlParser:true, useUnifiedTopology:true});

var letterSchema = mongoose.Schema({
  Email: {
    type: String,
    required: true
  },

  lvl_1: {
    type: Number
  },

  lvl_2: {
    type: Number
  },

  lvl_3: {
    type: Number
  },

  lvl_4: {
    type: Number
  },

  lvl_5: {
    type: Number
  },

  lvl_6: {
    type: Number
  }
});

var Letter_model = mongoose.model("Diacritic", letterSchema);

/* GET users listing. */

router.post("/showAccuracy", function(req, res) {
  var post_data = req.body;
  var user_Email = post_data.Email;

  Letter_model.find({ Email: user_Email })
    .count()
    .exec(function(err, a) {
      if (a == 0) {
        res.json({
          info: { msg: "account not found" }
        });
      } else if (a != 0) {
        Letter_model.find({ Email: user_Email }).exec(function(err, a) {
          if (err) throw err;

          var add = 0;
          var count = 0;

          const regex = /^lvl_([1-9]|10)$/;
          for (var prop in a[0]) {
            if (regex.exec(prop)) {
              if (a[0][prop] == undefined) {
                continue;
              }
              //console.log(a[0][prop]);
              count++;
              add += a[0][prop];
            }
          }
          var avg = add / count + "%";
          console.log({ msg: "letters avg accuracy", accuracy: avg });
          //console.log(a);
          res.json({
            info: { msg: "letters avg accuracy", accuracy: avg }
          });
        });
      }
    });
});

router.post("/adddb", function(req, res) {
  var post_data = req.body;
  var user_Email = post_data.Email;
  var user_Level = post_data.Level;
  var user_acc_value = post_data.Acc_value;

  Letter_model.find({ Email: user_Email })
    .count()
    .exec(function(err, rec) {
      if (err) throw err;
      if (rec != 0) {
        //res.json("updating existing " + rec);
        console.log("updating existing");

        Letter_model.findOneAndUpdate(
          { Email: user_Email },
          { [user_Level]: user_acc_value },
          function(err) {
            if (err) throw err;
            console.log("diacritic accuracy updated successfully");
            res.json({
              info: { msg: "accuracy updated successfully" }
            });
          }
        );
      } else if (rec == 0) {
        var user_accuracy = new Letter_model({
          Email: user_Email,
          [user_Level]: user_acc_value
        });
        user_accuracy.save(function(err, data) {
          if (err) {
            console.log(err + "error in adding");
          } else {
            console.log("diacritic accuracy updated successfully");
            res.json({
              info: { msg: "accuracy updated successfully" }
            });
          }
        });
      }
    });
});

module.exports = router;
