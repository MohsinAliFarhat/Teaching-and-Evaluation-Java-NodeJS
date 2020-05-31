//All imports
const express = require("express");
var mongoose = require("mongoose");
var bodyParser = require("body-parser");
var login = require("./routes/logIn")
const ffmpegPath = require('@ffmpeg-installer/ffmpeg').path;
const ffmpeg = require('fluent-ffmpeg');
ffmpeg.setFfmpegPath(ffmpegPath);
var send_email = require("./send-email.js");
var signUp = require("./routes/signUp")
const updatePassword = require("./routes/updatePassword");
var letterRouter = require("./routes/letters.js");
var diacriticsRouter = require("./routes/diacritics.js");
var wordRouter = require("./routes/words.js");


//Connection to mongoDB
mongoose
.connect("mongodb://localhost:27017/QuranDb" , {useNewUrlParser:true, useUnifiedTopology:true})
.then(()=>console.log("MongoDB connected."))
.catch(err=>console.log(err));

var fs= require('fs');
var chunks=[];
var path='python-scripts/audio.mp4';

//Creating express service
const app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}))


//Handling requests from android
app.use("/register",signUp);
app.use("/login",login);
app.use("/send-email",send_email);
app.use("/letters",letterRouter);
app.use("/diacritics",diacriticsRouter);
app.use("/words",wordRouter);
app.use("/update-password",updatePassword);

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

//Prediction Scripts

    app.post('/letters-upload-0',function(req,res){
      

      chunks=[];
      req.on('data',function(chunk){
        chunks.push(chunk);
      
      });
      
      req.on('end',function(){
        var data=Buffer.concat(chunks);
   fs.writeFile(path,data,'binary',function(err){
     if(err)
       console.log('couldnt make file'+err);
     else{
       console.log("Audio Recieved:");
       console.log(data);
       ffmpeg()
       .input('python-scripts/audio.mp4')
       .output('python-scripts/mono.wav')
       .run();
     
      //Code to run python code and perform prediction

      var exec = require("child_process").exec; 
      //running python script
      console.log(`[INFO] Computing`);
      exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_simple_1.py`,function(err,stdout,stderr){
        if(err){
            console.log(err);
            return;
        }

        console.log("[INFO] Computed ");
        console.log(stdout)
        res.send(stdout.toString());


      });

     }
   });
      });
 });


 app.post('/letters-upload-1',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_simple_2.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());


  });

 }
});
  });
});

app.post('/letters-upload-2',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_simple_3.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());

  });

 }
});
  });
});

app.post('/letters-upload-3',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_simple_4.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());


  });

 }
});
  });
});

app.post('/letters-upload-4',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_simple_5.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());


  });





 }
});
  });
});

app.post('/letters-upload-5',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_simple_6.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());


  });


 }
});
  });
});

app.post('/letters-upload-6',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_simple_7.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());


  });

 }
});
  });
});

app.post('/letters-upload-7',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_simple_8.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());


  });

 }
});
  });
});

app.post('/letters-upload-8',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_simple_9.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());


  });


 }
});
  });
});

app.post('/letters-upload-9',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_simple_10.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());


  });

 }
});
  });

});

app.post('/diacritics-upload-0',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_diacritic_1.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());


  });


 }
});
  });
  
});


app.post('/diacritics-upload-1',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_diacritic_2.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());


  });


 }
});
  });
  
});

app.post('/diacritics-upload-2',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_diacritic_3.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());


  });

 }
});
  });
  
});


app.post('/diacritics-upload-3',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_diacritic_4`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());


  }); }});  });});

app.post('/diacritics-upload-4',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_diacritic_5.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());


  });

 }
});
  });
  
});


app.post('/words-upload-0',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("word 0 Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_words_1.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    
    res.send(stdout.toString());

  });

 }
});
  });
  
});
app.post('/words-upload-1',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("word 1 Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\segmented_words_2.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());

  });

 }
});
  });
  
});
app.post('/words-upload-2',function(req,res){
      

  chunks=[];
  req.on('data',function(chunk){
    chunks.push(chunk);
  
  });
  
  req.on('end',function(){
    var data=Buffer.concat(chunks);
fs.writeFile(path,data,'binary',function(err){
 if(err)
   console.log('couldnt make file'+err);
 else{
   console.log("word 2 Audio Recieved:");
   console.log(data);
   ffmpeg()
   .input('python-scripts/audio.mp4')
   .output('python-scripts/mono.wav')
   .run();
 
  //Code to run python code and perform prediction

  var exec = require("child_process").exec; 
  //running python script
  console.log(`[INFO] Computing`);
  exec(`C:\\Users\\MOHSIN\\Anaconda3\\python.exe python-scripts\\words_3.py`,function(err,stdout,stderr){
    if(err){
        console.log(err);
        return;
    }

    console.log("[INFO] Computed ");
    console.log(stdout)
    res.send(stdout.toString());

  });

 }
});
  });
  
});


app.listen(3000, () => {
  console.log(
    "WebService Running on port 3000"
  );
});

app.get("/", (req, res) => {
  res.send("Welcome to Quran Teaching and Evaluation");
});
