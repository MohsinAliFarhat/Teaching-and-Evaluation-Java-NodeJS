package com.example.quran;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

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

public class characters_evaluation extends AppCompatActivity {

    //Recording related variables

    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String fileName = null;
    private MediaPlayer   player = null;
    private MediaRecorder recorder = null;
    public static int cat = -1;
    private boolean startRecording = true;
    public static int level_send_to_server = -1;
    // Requesting permission to RECORD_AUDIO

    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    private PopupWindow mPopupWindow;
    private static final String SERVER = "http://192.168.43.85:3000/";
    View v = null;

    public int level = 0;

    ImageView image0  = null;
    ImageView image1  = null;
    ImageView image2  = null;
    GridLayout mainGrid;
    CardView cv = null;
    TextView levelno = null;

    Boolean flag  = false;
    AudioRecorder recorder_new;

    Drawable img1_bck = null;
    Drawable img2_bck = null;
    Drawable img3_bck = null;


    private Context mContext;
    private Activity mActivity;
    private LinearLayout mRelativeLayout;


    int letter_clicked = 0;

    int acc_0,acc_1,acc_2=-1;
    int current_lvl=0;
    String Email_of_user,lvl;
    double total,Acc_value=0.0;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyservice iMyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters_evaluation);

        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyservice.class);

        cv = (CardView) findViewById(R.id.alif);




        image0 = (ImageView)findViewById(R.id.image0);
        image1 = (ImageView)findViewById(R.id.image1);
        image2 = (ImageView)findViewById(R.id.image2);
        levelno  = (TextView)findViewById(R.id.levelno);


        img1_bck = image0.getBackground();
        img2_bck = image0.getBackground();
        img3_bck = image0.getBackground();

        Toolbar toolbar =  findViewById(R.id.oldtoolbar);
        toolbar.setTitle("Characters");
        toolbar.setLogo(R.drawable.book_pic);
        toolbar.setNavigationIcon(R.drawable.back_nav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();
            }
        });


        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/recording_.wav";
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);


        mContext = getApplicationContext();
        mActivity = characters_evaluation.this;
        mRelativeLayout = (LinearLayout) findViewById(R.id.rl);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.pop_up_window,null);

        mPopupWindow = new PopupWindow(
                customView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // Set an elevation value for popup window
        // Call requires API level 21
        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }

        ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                mPopupWindow.dismiss();
            }
        });



        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);



        findViewById(R.id.rl).post(new Runnable() {
            public void run() {
                mPopupWindow.showAtLocation(findViewById(R.id.rl), Gravity.CENTER, 0, 0);
            }
        });
        recorder_new=new AudioRecorder();

        v = findViewById(android.R.id.content).getRootView();


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
                    checkButtonClicked(finalI);


                }
            });
        }
    }





    private void send_letter_accu(String Email, String lvl,Double Acc_value) {

        compositeDisposable.add(iMyService.update_letter_accuracy(Email,lvl,Acc_value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {



                        JSONObject obj = new JSONObject(response);
                        JSONObject info  = obj.getJSONObject("info");
                        String msg = info.getString("msg");



                        if(msg.equals("accuracy updated successfully")){
                            Toast.makeText(characters_evaluation.this, "Accuracy updated successfully!", Toast.LENGTH_SHORT).show();



                        }else{
                            Toast.makeText(characters_evaluation.this, "Accuracy not updated", Toast.LENGTH_SHORT).show();

                        }

                    }
                }));


    }







    private void checkButtonClicked(int x)
    {



        letter_clicked = x;

        image0.setBackground(img1_bck);
        image1.setBackground(img2_bck);
        image2.setBackground(img3_bck);

        if(x!=3&&x!=4) {
            if (flag == true) {

                Toast.makeText(characters_evaluation.this, "Recording sent to server for Evaluation", Toast.LENGTH_SHORT).show();

            }


            if (!flag) {

                recorder_new.startRecording();
                flag = true;
                Toast.makeText(characters_evaluation.this, "Recording Started", Toast.LENGTH_SHORT).show();


            } else {

                new uploadingAudioTask(characters_evaluation.this).execute();
                flag = false;

            }


        }


        if(x == 3)

        {



            total = acc_0+acc_1+acc_2;
            total = (total/3)*100;




            current_lvl=level;
            current_lvl++;
            lvl= "lvl_"+current_lvl;
            Acc_value=total;
            Email_of_user= userEmail;

            if(acc_0!=-1 && acc_1!=-1 && acc_2!=-1) {
                send_letter_accu(Email_of_user,lvl,Acc_value);
            }else{
//                Toast.makeText(this,"Accuracy not saved(Attempt all Letters to save accuracy)",Toast.LENGTH_SHORT).show();
            }





            total = 0;
            acc_0=-1;
            acc_1=-1;
            acc_2=-1;





            if(level<9) {
                level++;
                levelno.setText("Lesson - "+(level+1));
            }
            if(level==1) {
                image0.setImageResource(R.drawable.a004);
                image1.setImageResource(R.drawable.a005);
                image2.setImageResource(R.drawable.a006);


            }else if(level ==2){
                image0.setImageResource(R.drawable.a007);
                image1.setImageResource(R.drawable.a008);
                image2.setImageResource(R.drawable.a009);
            }
            else if(level ==3){
                image0.setImageResource(R.drawable.a010);
                image1.setImageResource(R.drawable.a011);
                image2.setImageResource(R.drawable.a012);
            }
            else if(level ==4){
                image0.setImageResource(R.drawable.a013);
                image1.setImageResource(R.drawable.a014);
                image2.setImageResource(R.drawable.a015);
            }
            else if(level ==5){
                image0.setImageResource(R.drawable.a016);
                image1.setImageResource(R.drawable.a017);
                image2.setImageResource(R.drawable.a018);
            }
            else if(level ==6){
                image0.setImageResource(R.drawable.a019);
                image1.setImageResource(R.drawable.a020);
                image2.setImageResource(R.drawable.a021);
            }
            else if(level ==7){
                image0.setImageResource(R.drawable.a022);
                image1.setImageResource(R.drawable.a023);
                image2.setImageResource(R.drawable.a024);
            }
            else if(level ==8){
                image0.setImageResource(R.drawable.a025);
                image1.setImageResource(R.drawable.a026);
                image2.setImageResource(R.drawable.a027);
            }
            else if(level ==9){
                image0.setImageResource(R.drawable.a027);
                image1.setImageResource(R.drawable.a028);
                image2.setImageResource(R.drawable.a029);
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
                image0.setImageResource(R.drawable.a001);
                image1.setImageResource(R.drawable.a002);
                image2.setImageResource(R.drawable.a003);


            }

            else if(level==1) {
                image0.setImageResource(R.drawable.a004);
                image1.setImageResource(R.drawable.a005);
                image2.setImageResource(R.drawable.a006);


            }else if(level ==2){
                image0.setImageResource(R.drawable.a007);
                image1.setImageResource(R.drawable.a008);
                image2.setImageResource(R.drawable.a009);
            }
            else if(level ==3){
                image0.setImageResource(R.drawable.a010);
                image1.setImageResource(R.drawable.a011);
                image2.setImageResource(R.drawable.a012);
            }
            else if(level ==4){
                image0.setImageResource(R.drawable.a013);
                image1.setImageResource(R.drawable.a014);
                image2.setImageResource(R.drawable.a015);
            }
            else if(level ==5){
                image0.setImageResource(R.drawable.a016);
                image1.setImageResource(R.drawable.a017);
                image2.setImageResource(R.drawable.a018);
            }
            else if(level ==6){
                image0.setImageResource(R.drawable.a019);
                image1.setImageResource(R.drawable.a020);
                image2.setImageResource(R.drawable.a021);
            }
            else if(level ==7){
                image0.setImageResource(R.drawable.a022);
                image1.setImageResource(R.drawable.a023);
                image2.setImageResource(R.drawable.a024);
            }
            else if(level ==8){
                image0.setImageResource(R.drawable.a025);
                image1.setImageResource(R.drawable.a026);
                image2.setImageResource(R.drawable.a027);
            }
            else if(level ==9){
                image0.setImageResource(R.drawable.a027);
                image1.setImageResource(R.drawable.a028);
                image2.setImageResource(R.drawable.a029);
            }

        }


    }




    private class uploadingAudioTask extends AsyncTask<String,Void,String>{


        ProgressDialog dialog;

        public uploadingAudioTask(characters_evaluation activity){
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

            dialog.setCancelable(true);

            if(level==0) {

                if(letter_clicked==0){

                    checkAlif(s);
                }else if(letter_clicked==1){

                    checkBaa(s);

                }else if(letter_clicked==2){

                    checkTaa(s);

                }

            }

            else if(level==1) {

                if(letter_clicked==0){
                    checkSaa(s);
                }else if(letter_clicked==1){

                    checkJeem(s);
                }else if(letter_clicked==2){
                    checkHaa(s);
                }

            }else if(level ==2){
                if(letter_clicked==0){

                    checkKhaa(s);
                }else if(letter_clicked==1){

                    checkDaal(s);
                }else if(letter_clicked==2){

                    checkZaal(s);
                }
            }
            else if(level ==3){
                if(letter_clicked==0){

                    checkRaa(s);
                }else if(letter_clicked==1){

                    checkZaa(s);
                }else if(letter_clicked==2){

                    checkSeen(s);
                }
            }
            else if(level ==4){
                if(letter_clicked==0){

                    checkSheen(s);
                }else if(letter_clicked==1){

                    checkSawd(s);
                }else if(letter_clicked==2){

                    checkZawd(s);
                }
            }
            else if(level ==5){
                if(letter_clicked==0){

                    checkTaw(s);
                }else if(letter_clicked==1){

                    checkZaw(s);
                }else if(letter_clicked==2){

                    checkAien(s);
                }
            }
            else if(level ==6){
                if(letter_clicked==0){

                    checkGhaien(s);
                }else if(letter_clicked==1){

                    checkFaa(s);
                }else if(letter_clicked==2){

                    checkKawf(s);
                }
            }
            else if(level ==7){
                if(letter_clicked==0){

                    checkKaaf(s);
                }else if(letter_clicked==1){

                    checkLaam(s);
                }else if(letter_clicked==2){

                    checkMeem(s);
                }
            }
            else if(level ==8){
                if(letter_clicked==0){

                    checkNoon(s);
                }else if(letter_clicked==1){

                    checkWow(s);
                }else if(letter_clicked==2){

                    checkHay(s);
                }
            }
            else if(level ==9){
                if(letter_clicked==0){

                    checkHay2(s);
                }else if(letter_clicked==1){

                    checkHamza(s);
                }else if(letter_clicked==2){

                    checkYaa(s);
                }
            }

            level_send_to_server=-1;


        }
    }


    public void checkAlif(String sound){


        if(sound.equals("alif")){



            image0.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;

        }else{

            image0.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }

    }

    public void checkBaa(String sound){

        if(sound.equals("baa")){
            image1.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;

        }else{
            image1.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }

    }

    public void checkTaa(String sound){


        if(sound.equals("taa")){
            image2.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;

        }else{
            image2.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_2=0;

        }
    }


    public void checkSaa(String sound){


        if(sound.equals("saa")){

            image0.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;
        }else{

            image0.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }

    }
    public void checkJeem(String sound){


        if(sound.equals("jeem")){

            image1.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            image1.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }

    }
    public void checkHaa(String sound){


        if(sound.equals("haa")){

            image2.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            image2.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_2=0;

        }

    }
    public void checkKhaa(String sound){


        if(sound.equals("khaa")){

            image0.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;
        }else{

            image0.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }

    }
    public void checkDaal(String sound){


        if(sound.equals("daal")){

            image1.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            image1.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }

    }
    public void checkZaal(String sound){


        if(sound.equals("zaal")){

            image2.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            image2.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_2=0;

        }

    }
    public void checkRaa(String sound){


        if(sound.equals("raa")){

            image0.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;
        }else{

            image0.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }

    }
    public void checkZaa(String sound){


        if(sound.equals("zaa")){

            image1.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            image1.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }

    }
    public void checkSeen(String sound){


        if(sound.equals("seen")){

            image2.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            image2.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_2=0;


        }

    }
    public void checkSheen(String sound){


        if(sound.equals("sheen")){

            image0.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;
        }else{

            image0.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }

    }
    public void checkSawd(String sound){


        if(sound.equals("sawd")){

            image1.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            image1.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }

    }
    public void checkZawd(String sound){


        if(sound.equals("dawd")){

            image2.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            image2.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_2=0;

        }

    }
    public void checkTaw(String sound){


        if(sound.equals("taw")){

            image0.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;
        }else{

            image0.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }

    }
    public void checkZaw(String sound){


        if(sound.equals("zaw")){

            image1.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            image1.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }

    }
    public void checkAien(String sound){


        if(sound.equals("aien")){

            image2.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            image2.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_2=0;

        }

    }
    public void checkGhaien(String sound){


        if(sound.equals("ghaien")){

            image0.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;
        }else{

            image0.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }

    }
    public void checkFaa(String sound){


        if(sound.equals("faa")){

            image1.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            image1.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }

    }
    public void checkKawf(String sound){


        if(sound.equals("kawf")){

            image2.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            image2.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_2=0;

        }

    }
    public void checkKaaf(String sound){


        if(sound.equals("kaaf")){

            image0.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;
        }else{

            image0.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }

    }
    public void checkLaam(String sound){


        if(sound.equals("laam")){

            image1.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            image1.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }

    }
    public void checkMeem(String sound){


        if(sound.equals("meem")){

            image2.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            image2.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_2=0;

        }

    }
    public void checkNoon(String sound){


        if(sound.equals("noon")){

            image0.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;
        }else{

            image0.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }

    }
    public void checkWow(String sound){


        if(sound.equals("wow")){

            image1.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            image1.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }

    }
    public void checkHay2(String sound){


        if(sound.equals("hay")){

            image0.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_0=1;

        }else{

            image0.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_0=0;
        }

    }
    public void checkHay(String sound){


        if(sound.equals("hay")){

            image2.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            image2.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_2=0;

        }

    }
    public void checkHamza(String sound){


        if(sound.equals("hamza")){

            image1.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_1=1;
        }else{

            image1.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
            acc_1=0;
        }

    }
    public void checkYaa(String sound){


        if(sound.equals("yaa")){

            image2.setBackgroundResource(R.color.greenColor);
            Toast.makeText(characters_evaluation.this,"Correct Pronounciation",Toast.LENGTH_LONG).show();

            acc_2=1;
        }else{

            image2.setBackgroundResource(R.color.redColor);
            Toast.makeText(characters_evaluation.this,"Incorrect Pronounciation",Toast.LENGTH_LONG).show();
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
