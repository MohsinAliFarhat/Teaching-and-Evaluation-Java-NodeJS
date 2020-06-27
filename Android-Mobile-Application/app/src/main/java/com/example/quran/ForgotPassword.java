package com.example.quran;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.quran.Retrofit.IMyservice;
import com.example.quran.Retrofit.RetrofitClient;

import org.json.JSONObject;

import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {


  Button ok_btn;
  EditText email_forgot;
  EditText code_forgot;
  EditText new_password;

  int code_update;
  Random random  = new Random();



    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyservice iMyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);



        Toolbar toolbar =  findViewById(R.id.oldtoolbar);
        toolbar.setTitle("Forgot Password");

        toolbar.setNavigationIcon(R.drawable.back_nav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyservice.class);

        ok_btn = (Button)findViewById(R.id.btn_ok);
        email_forgot = (EditText)findViewById(R.id.email);
        code_forgot = (EditText)findViewById(R.id.code);
        new_password = (EditText)findViewById(R.id.newPassword);

        ok_btn.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {


        if(email_forgot.getVisibility()==View.VISIBLE){

           code_update = Math.abs(random.nextInt());
           sendMessage(code_update);





        }else if(code_forgot.getVisibility()==View.VISIBLE){

           if( code_forgot.getText().toString().equals(Integer.toString(code_update))) {

               Toast.makeText(this, "Enter new Password!",Toast.LENGTH_SHORT);
               code_forgot.setVisibility(View.GONE);
               new_password.setVisibility(View.VISIBLE);


           }else{

               Toast.makeText(this, "Wrong code entered!",Toast.LENGTH_SHORT).show();

           }
        }else{

            if(new_password.getText().toString().length()>4){
            Toast.makeText(this, "Password updated Successfully!",Toast.LENGTH_SHORT).show();


            update_password(email_forgot.getText().toString(),new_password.getText().toString());}
            else{
                Toast.makeText(this, "Please enter at least 5 characters!",Toast.LENGTH_SHORT).show();
            }
            finish();
        }



    }

    private void sendMessage( final int code) {
        final ProgressDialog dialog = new ProgressDialog(ForgotPassword.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                  sendNewMail(email_forgot.getText().toString(),Integer.toString(code));
                    dialog.dismiss();

                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();

    }

    private void sendNewMail(String email, String code) {

        compositeDisposable.add(iMyService.sendNewMail(email,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {

                        JSONObject obj = new JSONObject(response);

                        JSONObject info  = obj.getJSONObject("info");
                        String msg = info.getString("msg");
                        String code_rcv = info.getString("code");
                        String name = info.getString("name");

                        if(code_rcv.equals("1")){

                            email_forgot.setVisibility(View.GONE);
                            code_forgot.setVisibility(View.VISIBLE);
                        }


                        Toast.makeText(ForgotPassword.this, ""+msg, Toast.LENGTH_SHORT).show();

                    }
                }));


    }

    private void update_password(String email,String pass){


        compositeDisposable.add(iMyService.update_password(email,pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {


                        finish();


                    }
                }));

    }
}
