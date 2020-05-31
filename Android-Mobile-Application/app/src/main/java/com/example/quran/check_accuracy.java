package com.example.quran;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.quran.Retrofit.IMyservice;
import com.example.quran.Retrofit.RetrofitClient;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.example.quran.first_activity.userEmail;

public class check_accuracy extends AppCompatActivity {




    TextView letters_result= null;
    TextView diacritics_result= null;
    TextView words_result= null;


    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyservice iMyService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_accuracy);

        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyservice.class);

        Toolbar toolbar =  findViewById(R.id.oldtoolbar);
        toolbar.setTitle("Overall Accuracy");

        toolbar.setNavigationIcon(R.drawable.back_nav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        letters_result = (TextView) findViewById(R.id.letters_result);
        diacritics_result = (TextView)findViewById(R.id.diacritics_result);
        words_result = (TextView)findViewById(R.id.words_result);



        get_letter_accuracy(userEmail);
        get_diacritics_accuracy(userEmail);
        get_words_accuracy(userEmail);
    }



    private void get_letter_accuracy(String Email) {

        compositeDisposable.add(iMyService.get_letter_accuracy(Email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override

                    public void accept(String response) throws Exception {



                        JSONObject obj = new JSONObject(response);

                        JSONObject info  = obj.getJSONObject("info");
                        String msg = info.getString("msg");



                        if(msg.equals("letters avg accuracy")){

                            String acc = info.getString("accuracy");

                            if(acc.equals("0%")){
                                letters_result.setText("0 %");
                            }else if(acc.equals("50%")){
                                letters_result.setText("50 %");
                            }else if (acc.equals("100 %")){
                                letters_result.setText("100 %");
                            }
                                else {

                                letters_result.setText(acc.substring(0, 4) + " %");
                            }
                        }else{


                        }

                    }
                }));


    }




    private void get_diacritics_accuracy(String Email) {

        compositeDisposable.add(iMyService.get_diacritics_accuracy(Email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {



                        JSONObject obj = new JSONObject(response);

                        JSONObject info  = obj.getJSONObject("info");
                        String msg = info.getString("msg");



                        if(msg.equals("letters avg accuracy")){

                            String acc = info.getString("accuracy");



                            if(acc.equals("0%")){
                                diacritics_result.setText("0%");
                            }else if(acc.equals("50%")){
                                diacritics_result.setText("50 %");
                            }else if (acc.equals("100 %")){
                                diacritics_result.setText("100 %");
                            }else {

                                diacritics_result.setText(acc.substring(0, 4) + " %");
                            }
                        }else{


                        }

                    }
                }));


    }


    private void get_words_accuracy(String Email) {

        compositeDisposable.add(iMyService.get_words_accuracy(Email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {



                        JSONObject obj = new JSONObject(response);

                        JSONObject info  = obj.getJSONObject("info");
                        String msg = info.getString("msg");



                        if(msg.equals("words avg accuracy")){

                            String acc = info.getString("accuracy");


                            if(acc.equals("0%")){
                                words_result.setText("0%");
                            }else if(acc.equals("50%")){
                                words_result.setText("50 %");
                            }else if (acc.equals("100 %")){
                                words_result.setText("100 %");
                            }else {

                                words_result.setText(acc.substring(0, 4) + " %");
                            }


                        }else{

                        }

                    }
                }));


    }


}
