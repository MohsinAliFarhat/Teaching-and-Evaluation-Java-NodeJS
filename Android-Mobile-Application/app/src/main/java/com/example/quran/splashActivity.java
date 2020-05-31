package com.example.quran;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class splashActivity extends AppCompatActivity {

    TextView create_account = null;
    boolean flag = false;

    private String name_ = "";
    private String email_ ="";



    //    MaterialEditText edt_login_email,edt_login_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        SQLiteDatabase db = openOrCreateDatabase("quran", MODE_PRIVATE, null);
        String query = "select * from user";
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor!=null) {
                int column1 =0;
                int column2 =1;
                if (cursor.moveToFirst()) {
                    do {
                        email_ =cursor.getString(column1);
                        name_ =cursor.getString(column2);
                    } while (cursor.moveToNext());
                }
                    flag = true;

            }else {
                flag = false;
            }
        }catch (Exception ex){
            Toast.makeText(this,""+ex,Toast.LENGTH_LONG);
        }

        if (!flag) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent splashIntent = new Intent(splashActivity.this, log_in.class);
                    startActivity(splashIntent);
                    finish();
                }
            }, 2000);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent splashIntent = new Intent(splashActivity.this, first_activity.class);
                    splashIntent.putExtra("email",email_);
                    splashIntent.putExtra("name",name_);
                    startActivity(splashIntent);
                    finish();
                }
            }, 2000);

        }
    }

    public String getEmail(){
        return email_;
    }
    public String getName(){
        return name_;
    }
}