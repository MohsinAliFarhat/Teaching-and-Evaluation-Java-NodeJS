package com.example.quran;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.quran.Retrofit.IMyservice;
import com.example.quran.Retrofit.RetrofitClient;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class log_in extends AppCompatActivity {

    TextView create_acc;
    MaterialEditText email,pass;
    Button login;
    TextView forgot_password = null;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyservice iMyService;

    Intent intent = null;
    Intent forgot_intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyService = retrofitClient.create(IMyservice.class);
       intent = new Intent(this, first_activity.class);

        email = (MaterialEditText) findViewById(R.id.edt_email);
        pass = (MaterialEditText) findViewById(R.id.edt_password);

        login = (Button) findViewById(R.id.btn_login);
        forgot_password = (TextView)findViewById(R.id.txt_forgot_password);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser(email.getText().toString(),
                        pass.getText().toString());

            }
        });

        forgot_intent = new Intent(this, ForgotPassword.class);
        forgot_password.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               startActivity(forgot_intent);

            }
        });


        create_acc  = (TextView) findViewById(R.id.txt_create_account);

        create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View register_layout = LayoutInflater.from(log_in.this)
                        .inflate(R.layout.register_layout,null);

                new MaterialStyledDialog.Builder(log_in.this)
                        .setIcon(R.drawable.ic_user)
                        .setTitle("REGISTRATION")
                        .setDescription("Please fill all fields")
                        .setHeaderColor(R.color.white)
                        .setCustomView(register_layout)
                        .setNegativeText("CANCEL")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveText("REGISTER")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                MaterialEditText edt_register_email = (MaterialEditText)register_layout.findViewById(R.id.edt_email);
                                MaterialEditText edt_register_name = (MaterialEditText)register_layout.findViewById(R.id.edt_name);
                                MaterialEditText edt_register_password = (MaterialEditText)register_layout.findViewById(R.id.edt_password);


                                if(TextUtils.isEmpty(edt_register_email.getText()))
                                {
                                    Toast.makeText(log_in.this,"Email cannot be empty",Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                if(TextUtils.isEmpty(edt_register_name.getText()))
                                {
                                    Toast.makeText(log_in.this,"Name cannot be empty",Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                if(TextUtils.isEmpty(edt_register_password.getText()))
                                {
                                    Toast.makeText(log_in.this,"Password cannot be empty",Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                registerUser(edt_register_email.getText().toString(),
                                        edt_register_name.getText().toString(),
                                        edt_register_password.getText().toString());

                            }
                        }).show();

            }
        });





    }





    private  void loginUser(String email, String password)
    {

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Email cannot be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Password cannot be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        compositeDisposable.add(iMyService.loginUser(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {



                        JSONObject obj = new JSONObject(response);

                        JSONObject info  = obj.getJSONObject("info");
                        String msg = info.getString("msg");
                        String email = info.getString("email");
                        String name = info.getString("name");


                        if(msg.equals("Login Success")){
                            Toast.makeText(log_in.this, "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                            SQLiteDatabase db = openOrCreateDatabase("quran", MODE_PRIVATE, null);
                            db.execSQL("create table if not exists user(email varchar(30),name varchar(20))");
                            db.execSQL("insert into user(email, name)  values ("+"'"+email+"'"+","+"'"+name+"');");

                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("email",email);
                            intent.putExtra("name",name);
                            startActivity(intent);


                        }else{
                            Toast.makeText(log_in.this, "Wrong email or password!", Toast.LENGTH_SHORT).show();

                        }

                    }
                }));

    }

    private void registerUser(String email, String name, String password) {

        compositeDisposable.add(iMyService.registerUser(email,name,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {

                        Toast.makeText(log_in.this, ""+response, Toast.LENGTH_SHORT).show();

                    }
                }));
    }

}
