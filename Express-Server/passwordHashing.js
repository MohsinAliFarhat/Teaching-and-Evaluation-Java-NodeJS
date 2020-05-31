const crypto = require("crypto");

checkHashPassword = (userPassword, salt) => {
    var passwordData = sha512(userPassword, salt);
    return passwordData;
  }

  saltHashPassword = (userPassword) => {
    var salt = genRandomString(16); // CREATE 16 RANDOM CHARECTER
    var passwordData = sha512(userPassword, salt);
    return passwordData;
  }

  var sha512 = function(password, salt) {
    var hash = crypto.createHmac("sha512", salt);
    hash.update(password);
    var value = hash.digest("hex");
    return {
      salt: salt,
      passwordHash: value
    };
  };

  
var genRandomString = function(length) {
    return crypto
      .randomBytes(Math.ceil(length / 2))
      .toString("hex") /*CONVERT TO HEXA FORMAT*/
      .slice(0, length);
  };
  module.exports = checkHashPassword;