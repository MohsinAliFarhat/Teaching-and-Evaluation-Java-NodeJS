package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class supplication extends AppCompatActivity {

    Intent intent = null;
    TextView arabic = null;
    TextView translation = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplication);

        arabic = (TextView)findViewById(R.id.arabic_dua);
        translation = (TextView)findViewById(R.id.translation_dua);
        intent = getIntent();
        Toolbar toolbar =  findViewById(R.id.oldtoolbar);
        toolbar.setTitle(intent.getStringExtra("dua"));;
        toolbar.setNavigationIcon(R.drawable.back_nav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        arabic.setText(intent.getStringExtra("arabic"));
        translation.setText(intent.getStringExtra("translation"));
    }
}
