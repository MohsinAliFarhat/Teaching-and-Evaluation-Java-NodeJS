package com.example.quran;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

public class words_evaluation extends AppCompatActivity {
    ImageView image0  = null;
    ImageView image1  = null;
    ImageView image2  = null;


    public int level = 0;
    int words_clicked = 0;

    TextView levelno = null;
    GridLayout mainGrid;
    MediaPlayer player;

    Drawable img1_bck = null;
    Drawable img2_bck = null;
    Drawable img3_bck = null;

    Boolean flag  = false;
    public static int level_send_to_server =-1;

    AudioRecorder recorder_new;

    int acc_0,acc_1,acc_2=-1;
    int current_lvl=0;
    String Email_of_user,lvl;
    double total,Acc_value=0.0;
    private CardView cardView = null;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyservice iMyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_evaluation);


        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyservice.class);



        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        image0 = (ImageView)findViewById(R.id.word0) ;
        image1 = (ImageView)findViewById(R.id.word1) ;
        image2 = (ImageView)findViewById(R.id.word2) ;
        levelno = (TextView)findViewById(R.id.levelno);

        img1_bck = image0.getBackground();
        img2_bck = image0.getBackground();
        img3_bck = image0.getBackground();

        setSingleEvent(mainGrid);

        Toolbar toolbar =  findViewById(R.id.oldtoolbar);
        toolbar.setTitle("  Words");
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
            cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {

                    setWhiteBackGround();
                    playAudio(finalI);


                }
            });
        }
    }



    private void send_word_accu(String Email, String lvl,Double Acc_value) {

        compositeDisposable.add(iMyService.update_word_accuracy(Email,lvl,Acc_value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {

                        JSONObject obj = new JSONObject(response);
                        JSONObject info  = obj.getJSONObject("info");
                        String msg = info.getString("msg");

                        if(msg.equals("accuracy updated successfully")){
                            Toast.makeText(words_evaluation.this, "accuracy updated successfully!", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(words_evaluation.this, "accuracy not updated", Toast.LENGTH_SHORT).show();

                        }

                    }
                }));


    }


    private void playAudio(int x)
    {

        words_clicked = x;
        image0.setBackground(img1_bck);
        image1.setBackground(img2_bck);
        image2.setBackground(img3_bck);
        cardView = (CardView) mainGrid.getChildAt(words_clicked);
        cardView.setCardBackgroundColor(0xA7A7A7);
        if(x!= 3 && x!=4)
        {

            if (flag == true) {

                Toast.makeText(words_evaluation.this, "Recording sent to server for Evaluation", Toast.LENGTH_SHORT).show();

            }


            if (!flag) {

                recorder_new.startRecording();
                flag = true;
                Toast.makeText(words_evaluation.this, "Recording Started", Toast.LENGTH_SHORT).show();



            } else {

                new words_evaluation.uploadingAudioTask(words_evaluation.this).execute();
                flag = false;

            }

        }

        else if(x == 3)
        {


            total = acc_0+acc_1+acc_2;
            total = (total/3)*100;


            String accuracy_previous_level = "N/A";

                current_lvl=level;
            current_lvl++;
            lvl= "lvl_"+current_lvl;
            Acc_value=total;
            Email_of_user= userEmail;
            if(acc_0!=-1 && acc_1!=-1 && acc_2!=-1) {
                accuracy_previous_level = (total)+"%";
                send_word_accu(Email_of_user,lvl,Acc_value);

            }else{
//                Toast.makeText(this,"Accuracy not saved(Attempt all words to save accuracy)",Toast.LENGTH_SHORT).show();
            }

;

            total = 0;
            acc_0=-1;
            acc_1=-1;
            acc_2=-1;



            if(level<2) {
                level++;
                levelno.setText("Lesson - "+(level+1));
                String next = (level+2)+"";
                if(level==2){
                    next = "N/A";
                }
                new AlertDialog.Builder(this)
                        .setTitle("Lesson Information")
                        .setMessage("Current Lesson          : "+(level+1)+"\n"
                                +   "Lesson(s) Remaining: "+(3-(level+1))+"\n"
                                +   "Next Lesson                : "+(next)+"\n"
                                +   "Accuracy of previous Lesson: "+accuracy_previous_level)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                            }
                        })
//                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

            if(level==1){
                image0.setImageResource(R.drawable.f004);
                image1.setImageResource(R.drawable.f005);
                image2.setImageResource(R.drawable.f006);
            }
            else if(level==2){
                image0.setImageResource(R.drawable.f007);
                image1.setImageResource(R.drawable.f008);
                image2.setImageResource(R.drawable.f009);
            }
            else if(level==3){
                image0.setImageResource(R.drawable.f010);
                image1.setImageResource(R.drawable.f011);
                image2.setImageResource(R.drawable.f012);
            }
            else if(level==4){
                image0.setImageResource(R.drawable.f013);
                image1.setImageResource(R.drawable.f014);
                image2.setImageResource(R.drawable.f015);
            }
            else if(level==5){
                image0.setImageResource(R.drawable.f016);
                image1.setImageResource(R.drawable.f017);
                image2.setImageResource(R.drawable.f018);
            }else if(level==6){
                image0.setImageResource(R.drawable.f018);
                image1.setImageResource(R.drawable.f019);
                image2.setImageResource(R.drawable.f020);
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
                image0.setImageResource(R.drawable.f001);
                image1.setImageResource(R.drawable.f002);
                image2.setImageResource(R.drawable.f003);


            }

            else if(level==1) {
                image0.setImageResource(R.drawable.f004);
                image1.setImageResource(R.drawable.f005);
                image2.setImageResource(R.drawable.f006);


            }else if(level ==2){
                image0.setImageResource(R.drawable.f007);
                image1.setImageResource(R.drawable.f008);
                image2.setImageResource(R.drawable.f009);
            }
            else if(level ==3){
                image0.setImageResource(R.drawable.f010);
                image1.setImageResource(R.drawable.f011);
                image2.setImageResource(R.drawable.f012);
            }
            else if(level ==4){
                image0.setImageResource(R.drawable.f013);
                image1.setImageResource(R.drawable.f014);
                image2.setImageResource(R.drawable.f015);
            }
            else if(level ==5){
                image0.setImageResource(R.drawable.f016);
                image1.setImageResource(R.drawable.f017);
                image2.setImageResource(R.drawable.f018);
            }
            else if(level ==6){
                image0.setImageResource(R.drawable.f018);
                image1.setImageResource(R.drawable.f019);
                image2.setImageResource(R.drawable.f020);
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

        public uploadingAudioTask(words_evaluation activity){
            dialog = new ProgressDialog(activity);
            dialog.setTitle("Waiting for response from server...");
            dialog.setMessage("Please wait");

        }

        @Override
        protected void onPreExecute(){

            dialog.show();
            dialog.setCancelable(false);


        }

        @Override
        protected String doInBackground(String... params) {
            level_send_to_server=level;
            String res = recorder_new.stopRecording();
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            cardView = (CardView) mainGrid.getChildAt(words_clicked);

            if(level==0) {

                if(words_clicked==0){

                    checkNazala(s);
                }else if(words_clicked==1){

                    checkKhalaqa(s);

                }else if(words_clicked==2){

                    checkSadaqa(s);
                }
            }
            else if(level==1) {

                if(words_clicked==0){
                    checkYadaka(s);
                }else if(words_clicked==1){

                    checkBalagha(s);
                }else if(words_clicked==2){
                    checkTabaaw(s);
                }

            }else if(level ==2){
                if(words_clicked==0){

                    checkJaawla(s);
                }else if(words_clicked==1){

                    checkFaawla(s);
                }else if(words_clicked==2){

                    checkNazara(s);
                }
            }
            level_send_to_server=-1;
        }

    }


    public void checkNazala(String sound){


        if(sound.equals("nazala")){

            cardView.setCardBackgroundColor(Color.parseColor("#57FF22"));
            Toast.makeText(words_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;

        }else{

            cardView.setCardBackgroundColor(Color.parseColor("#FF4242"));
            Toast.makeText(words_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }

    }

    public void checkKhalaqa(String sound){

        if(sound.equals("khalaqa")){
            cardView.setCardBackgroundColor(Color.parseColor("#57FF22"));
            Toast.makeText(words_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;

        }else{
            cardView.setCardBackgroundColor(Color.parseColor("#FF4242"));
            Toast.makeText(words_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }

    }

    public void checkSadaqa(String sound){


        if(sound.equals("sadaqa")){
            cardView.setCardBackgroundColor(Color.parseColor("#57FF22"));
            Toast.makeText(words_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;

        }else{
            cardView.setCardBackgroundColor(Color.parseColor("#FF4242"));
            Toast.makeText(words_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_2=0;

        }
    }


    public void checkYadaka(String sound){


        if(sound.equals("yadaka")){

            cardView.setCardBackgroundColor(Color.parseColor("#57FF22"));
            Toast.makeText(words_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;
        }else{

            cardView.setCardBackgroundColor(Color.parseColor("#FF4242"));
            Toast.makeText(words_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }

    }
    public void checkBalagha(String sound){


        if(sound.equals("balagha")){

            cardView.setCardBackgroundColor(Color.parseColor("#57FF22"));
            Toast.makeText(words_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            cardView.setCardBackgroundColor(Color.parseColor("#FF4242"));
            Toast.makeText(words_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }

    }
    public void checkTabaaw(String sound){


        if(sound.equals("tabaaw")){

            cardView.setCardBackgroundColor(Color.parseColor("#57FF22"));
            Toast.makeText(words_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            cardView.setCardBackgroundColor(Color.parseColor("#FF4242"));
            Toast.makeText(words_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_2=0;

        }

    }
    public void checkJaawla(String sound){


        if(sound.equals("jaawla")){

            cardView.setCardBackgroundColor(Color.parseColor("#57FF22"));
            Toast.makeText(words_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;
        }else{

            cardView.setCardBackgroundColor(Color.parseColor("#FF4242"));
            Toast.makeText(words_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }

    }
    public void checkFaawla(String sound){


        if(sound.equals("faawla")){

            cardView.setCardBackgroundColor(Color.parseColor("#57FF22"));
            Toast.makeText(words_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            cardView.setCardBackgroundColor(Color.parseColor("#FF4242"));
            Toast.makeText(words_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }

    }

    public void checkNazara(String sound){


        if(sound.equals("nazara")){

            cardView.setCardBackgroundColor(Color.parseColor("#57FF22"));
            Toast.makeText(words_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            cardView.setCardBackgroundColor(Color.parseColor("#FF4242"));
            Toast.makeText(words_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
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
