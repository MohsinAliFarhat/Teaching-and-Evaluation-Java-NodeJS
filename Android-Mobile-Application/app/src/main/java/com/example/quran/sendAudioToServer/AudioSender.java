package com.example.quran.sendAudioToServer;

import android.util.Log;

import com.example.quran.characters_diacritics_evaluation;
import com.example.quran.characters_evaluation;
import com.example.quran.words_evaluation;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;


public class AudioSender implements Callable<String> {

    String outputFile;
    AudioSender(String fileLocation){
        outputFile=fileLocation;
    }

    public String call(){

    File output=new File(outputFile);
    String response = new String ();
    try {
    MultipartUtility multipart = null;

    if(characters_evaluation.level_send_to_server!=-1){

            if(characters_evaluation.level_send_to_server==0) {
                multipart = new MultipartUtility("http://192.168.43.85:3000/letters-upload-0");

            }else if(characters_evaluation.level_send_to_server==1){
                multipart = new MultipartUtility("http://192.168.43.85:3000/letters-upload-1");
            }
            else if(characters_evaluation.level_send_to_server==2){
                multipart = new MultipartUtility("http://192.168.43.85:3000/letters-upload-2");
            }
            else if(characters_evaluation.level_send_to_server==3){
                multipart = new MultipartUtility("http://192.168.43.85:3000/letters-upload-3");
            }
            else if(characters_evaluation.level_send_to_server==4){
                multipart = new MultipartUtility("http://192.168.43.85:3000/letters-upload-4");
            }
            else if(characters_evaluation.level_send_to_server==5){
                multipart = new MultipartUtility("http://192.168.43.85:3000/letters-upload-5");
            }
            else if(characters_evaluation.level_send_to_server==6){
                multipart = new MultipartUtility("http://192.168.43.85:3000/letters-upload-6");
            }else if(characters_evaluation.level_send_to_server==7){
                multipart = new MultipartUtility("http://192.168.43.85:3000/letters-upload-7");
            }else if(characters_evaluation.level_send_to_server==8){
                multipart = new MultipartUtility("http://192.168.43.85:3000/letters-upload-8");
            }else if(characters_evaluation.level_send_to_server==9){
                multipart = new MultipartUtility("http://192.168.43.85:3000/letters-upload-9");
            }

    }else if(characters_diacritics_evaluation.level_send_to_server!=-1){


            if(characters_diacritics_evaluation.level_send_to_server==0) {
                multipart = new MultipartUtility("http://192.168.43.85:3000/diacritics-upload-0");

            }else if(characters_diacritics_evaluation.level_send_to_server==1){
                multipart = new MultipartUtility("http://192.168.43.85:3000/diacritics-upload-1");
            }
            else if(characters_diacritics_evaluation.level_send_to_server==2){
                multipart = new MultipartUtility("http://192.168.43.85:3000/diacritics-upload-2");
            }
            else if(characters_diacritics_evaluation.level_send_to_server==3){
                multipart = new MultipartUtility("http://192.168.43.85:3000/diacritics-upload-3");
            }
            else if(characters_diacritics_evaluation.level_send_to_server==4){
                multipart = new MultipartUtility("http://192.168.43.85:3000/diacritics-upload-4");
            }


            }


    else{

        if(words_evaluation.level_send_to_server==0) {
            multipart = new MultipartUtility("http://192.168.43.85:3000/words-upload-0");

        }else if(words_evaluation.level_send_to_server==1){
            multipart = new MultipartUtility("http://192.168.43.85:3000/words-upload-1");
        }
        else if(words_evaluation.level_send_to_server==2){
            multipart = new MultipartUtility("http://192.168.43.85:3000/words-upload-2");
        }




    }

            multipart.addFilePart("audio", output);
            response = multipart.finish();
        }

        catch(IOException e){

            e.printStackTrace();

        }

        finally {


            Log.d("SR", "SERVER REPLIED:");


            return response;
        }

    }
}
