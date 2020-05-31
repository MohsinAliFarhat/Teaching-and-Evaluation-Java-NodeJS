package com.example.quran;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.quran.Retrofit.IMyservice;
import com.example.quran.Retrofit.RetrofitClient;
import com.example.quran.sendAudioToServer.AudioRecorder;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.example.quran.first_activity.userEmail;

public class characters_diacritics_evaluation extends AppCompatActivity {

    GridLayout mainGrid;
    MediaPlayer player;


    ImageView image0  = null;
    ImageView image1  = null;
    ImageView image2  = null;
    TextView levelno = null;
    Boolean flag  = false;

    public int level = 0;
    public static int level_send_to_server = -1;


    Drawable img1_bck = null;
    Drawable img2_bck = null;
    Drawable img3_bck = null;

    int diacritic_clicked = 0;
    AudioRecorder recorder_new;




    /////////////////////////////////////
    int acc_0,acc_1,acc_2=-1;
    int current_lvl=0;
    String Email_of_user,lvl;
    double total,Acc_value=0.0;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyservice iMyService;
    /////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters_diacritics_evaluation);


        //////////////////////////////////////////////////////////////////////
        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyservice.class);



        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        image0 = (ImageView)findViewById(R.id.diacritic0) ;
        image1 = (ImageView)findViewById(R.id.diacritic1) ;
        image2 = (ImageView)findViewById(R.id.diacritic2) ;
        levelno = (TextView)findViewById(R.id.levelno);
        img1_bck = image0.getBackground();
        img2_bck = image0.getBackground();
        img3_bck = image0.getBackground();


        setSingleEvent(mainGrid);

        Toolbar toolbar =  findViewById(R.id.oldtoolbar);
        toolbar.setTitle("Characters with Diacritics");
        toolbar.setLogo(R.drawable.book_pic);
        toolbar.setNavigationIcon(R.drawable.back_nav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




        recorder_new=new AudioRecorder();


    }


    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount()-1; i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
          final  CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {

                    setWhiteBackGround();
                    cardView.setCardBackgroundColor(0xA7A7A7);
                    playAudio(finalI);



                }
            });
        }
    }



    private void send_diacritic_accu(String Email, String lvl,Double Acc_value) {

        compositeDisposable.add(iMyService.update_diacritic_accuracy(Email,lvl,Acc_value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {



                        JSONObject obj = new JSONObject(response);

                        JSONObject info  = obj.getJSONObject("info");
                        String msg = info.getString("msg");


                        if(msg.equals("accuracy updated successfully")){
                            Toast.makeText(characters_diacritics_evaluation.this, "Accuracy updated successfully!", Toast.LENGTH_SHORT).show();



                        }else{
                            Toast.makeText(characters_diacritics_evaluation.this, "Accuracy not updated", Toast.LENGTH_SHORT).show();

                        }

                    }
                }));


    }







    private void playAudio(int x)
    {

        image0.setBackground(img1_bck);
        image1.setBackground(img2_bck);
        image2.setBackground(img3_bck);

        diacritic_clicked = x;

        if(x!=3&&x!=4) {
            if (flag == true) {

                Toast.makeText(characters_diacritics_evaluation.this, "Recording sent to server for Evaluation", Toast.LENGTH_SHORT).show();

            }


            if (!flag) {

                recorder_new.startRecording();
                flag = true;
                Toast.makeText(characters_diacritics_evaluation.this, "Recording Started", Toast.LENGTH_SHORT).show();


            } else {

                new characters_diacritics_evaluation.uploadingAudioTask(characters_diacritics_evaluation.this).execute();
                flag = false;

            }


        }

        else if(x == 3)
        {


            total = acc_0+acc_1+acc_2;
            total = (total/3)*100;




            current_lvl=level;
            current_lvl++;
            lvl= "lvl_"+current_lvl;
            Acc_value=total;
            Email_of_user= userEmail;

    if(acc_0!=-1 && acc_1!=-1 && acc_2!=-1) {
        send_diacritic_accu(Email_of_user, lvl, Acc_value);
    }else{
//        Toast.makeText(this,"Accuracy not saved(Attempt all diacritics to save accuracy)",Toast.LENGTH_SHORT).show();
    }


            total = 0;
            acc_0=-1;
            acc_1=-1;
            acc_2=-1;




            if(level<4) {
                level++;
                levelno.setText("Lesson - "+(level+1));
            }
            if(level==1){
                image0.setImageResource(R.drawable.c004);
                image1.setImageResource(R.drawable.c005);
                image2.setImageResource(R.drawable.c006);
            }
            else if(level==2){
                image0.setImageResource(R.drawable.c007);
                image1.setImageResource(R.drawable.c008);
                image2.setImageResource(R.drawable.c009);
            }
            else if(level==3){
                image0.setImageResource(R.drawable.c010);
                image1.setImageResource(R.drawable.c011);
                image2.setImageResource(R.drawable.c012);
            }
            else if(level==4){
                image0.setImageResource(R.drawable.c013);
                image1.setImageResource(R.drawable.c014);
                image2.setImageResource(R.drawable.c015);
            }
            else if(level==5){
                image0.setImageResource(R.drawable.c016);
                image1.setImageResource(R.drawable.c017);
                image2.setImageResource(R.drawable.c018);
            }else if(level==6){
                image0.setImageResource(R.drawable.c018);
                image1.setImageResource(R.drawable.c019);
                image2.setImageResource(R.drawable.c020);
            }


        }else if(x==4){


            total = 0;
            acc_0=-1;
            acc_1=-1;
            acc_2=-1;



            if(level>=1){
                level--;

                levelno.setText("Lesson - " + (level + 1));

            }
            if(level==0) {
                image0.setImageResource(R.drawable.c001);
                image1.setImageResource(R.drawable.c002);
                image2.setImageResource(R.drawable.c003);


            }

            else if(level==1) {
                image0.setImageResource(R.drawable.c004);
                image1.setImageResource(R.drawable.c005);
                image2.setImageResource(R.drawable.c006);


            }else if(level ==2){
                image0.setImageResource(R.drawable.c007);
                image1.setImageResource(R.drawable.c008);
                image2.setImageResource(R.drawable.c009);
            }
            else if(level ==3){
                image0.setImageResource(R.drawable.c010);
                image1.setImageResource(R.drawable.c011);
                image2.setImageResource(R.drawable.c012);
            }
            else if(level ==4){
                image0.setImageResource(R.drawable.c013);
                image1.setImageResource(R.drawable.c014);
                image2.setImageResource(R.drawable.c015);
            }
            else if(level ==5){
                image0.setImageResource(R.drawable.c016);
                image1.setImageResource(R.drawable.c017);
                image2.setImageResource(R.drawable.c018);
            }
            else if(level ==6){
                image0.setImageResource(R.drawable.c018);
                image1.setImageResource(R.drawable.c019);
                image2.setImageResource(R.drawable.c020);
            }


        }
    }



    private void stopPlayer()
    {
        if(player != null)
        {
            player.release();
            player = null;
            Toast.makeText(this, "MediaPlayer Released", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        stopPlayer();
    }



    private class uploadingAudioTask extends AsyncTask<String,Void,String> {


        ProgressDialog dialog;

        public uploadingAudioTask(characters_diacritics_evaluation activity) {
            dialog = new ProgressDialog(activity);
            dialog.setTitle("Waiting for response from server...");
            dialog.setMessage("Please wait");

        }

        @Override
        protected void onPreExecute() {

            dialog.show();
            dialog.setCancelable(false);


        }

        @Override
        protected String doInBackground(String... params) {
            level_send_to_server = level;
            String res = recorder_new.stopRecording();
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();

            dialog.setCancelable(true);


            if(level==0){
                if(diacritic_clicked==0){

                    checkAa(s);
                }else if(diacritic_clicked==1){

                    checkIi(s);
                }else if(diacritic_clicked==2){

                    checkUu(s);
                }
            }
            else if(level==1){

                if(diacritic_clicked==0){

                    checkBaa(s);
                }else if(diacritic_clicked==1){

                    checkBii(s);
                }else if(diacritic_clicked==2){

                    checkBuu(s);
                }
            }
            else if(level==2){
                if(diacritic_clicked==0){

                    checkTaa(s);
                }else if(diacritic_clicked==1){

                    checkTii(s);
                }else if(diacritic_clicked==2){

                    checkTuu(s);
                }
            }
            else if(level==3){
                if(diacritic_clicked==0){

                    checkSaa(s);

                }else if(diacritic_clicked==1){

                    checkSi(s);
                }else if(diacritic_clicked==2){

                    checkSu(s);
                }
            }
            else if(level==4){
                if(diacritic_clicked==0){

                    checkJaa(s);
                }else if(diacritic_clicked==1){

                    checkJi(s);
                }else if(diacritic_clicked==2){

                    checkJu(s);
                }
            }

            level_send_to_server=-1;
        }
    }


    public void checkAa(String sound){

        if(sound.equals("aa")){

            image0.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;
        }else{

            image0.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }
    }
    public void checkIi(String sound){

        if(sound.equals("ii")){

            image1.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            image1.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }
    }
    public void checkUu(String sound){

        if(sound.equals("uu")){

            image2.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            image2.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();

        }
    }



    public void checkBaa(String sound){

        if(sound.equals("baa")){

            image0.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;
        }else{

            image0.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }
    }
    public void checkBii(String sound){

        if(sound.equals("bii")){

            image1.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            image1.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }
    }
    public void checkBuu(String sound){

        if(sound.equals("buu")){

            image2.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            image2.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_2=0;
        }
    }



    public void checkTaa(String sound){

        if(sound.equals("taa")){

            image0.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;
        }else{

            image0.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }
    }
    public void checkTii(String sound){

        if(sound.equals("tii")){

            image1.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            image1.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }
    }
    public void checkTuu(String sound){

        if(sound.equals("tuu")){

            image2.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            image2.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_2=0;
        }
    }



    public void checkSaa(String sound){

        if(sound.equals("saa")){

            image0.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;
        }else{

            image0.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }
    }
    public void checkSi(String sound){

        if(sound.equals("si")){

            image1.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            image1.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }
    }
    public void checkSu(String sound){

        if(sound.equals("su")){

            image2.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            image2.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_2=0;
        }
    }



    public void checkJaa(String sound){

        if(sound.equals("jaa")){

            image0.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;
        }else{

            image0.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }
    }
    public void checkJi(String sound){

        if(sound.equals("ji")){

            image1.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            image1.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }
    }
    public void checkJu(String sound){

        if(sound.equals("ju")){

            image2.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            image2.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_diacritics_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_2=0;
        }
    }


    private void setWhiteBackGround(){
        for (int i = 0; i < mainGrid.getChildCount()-1; i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

}