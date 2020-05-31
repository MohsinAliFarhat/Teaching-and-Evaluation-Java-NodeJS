package com.example.quran.sendAudioToServer;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;



public class AudioRecorder extends MediaRecorder {



    MediaRecorder recorder;
    String outputFile;
    public void startRecording(){
        recorder=new MediaRecorder();
        outputFile= Environment.getExternalStorageDirectory().getAbsolutePath()+"/recording_.wav";
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
//        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        recorder.setAudioEncodingBitRate(16*44100);
        recorder.setAudioSamplingRate(44100);
        try{
            recorder.setOutputFile(outputFile);
        }
        catch(Exception e){
            Log.e("Output File","failed");
        }

        try{
            recorder.prepare();
        }
        catch(IOException e){
            Log.e("Audio Record", "prepare() failed");
        }
        recorder.start();
    }

   public  String stopRecording(){
        recorder.stop();
        recorder.reset();
        recorder.release();
        String res = upload();
        return res;
    }

    void readFile(){
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(outputFile));
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        finally {
            if (in != null)
                try{
                    in.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
        }
    }

    public String upload() {
        String response=null;
        AudioSender sender=new AudioSender(outputFile);

        FutureTask<String> uploadTask=new FutureTask<String>(sender);
        ExecutorService executorService= Executors.newFixedThreadPool(1);
        executorService.execute(uploadTask);
        while(true){
            if(uploadTask.isDone()){
                try{
                    response=uploadTask.get();


                    break;
                }catch(InterruptedException| ExecutionException e){
                    e.printStackTrace();
                    Log.e("Upload","Exception",e.getCause());
                }
            }
        }

        return response;
    }
}
