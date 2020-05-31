package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class qari_accent_selection extends AppCompatActivity implements View.OnClickListener {

    CardView qari1 = null;
    CardView qari2 = null;
    Intent intent = null;
    Intent nextIntent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qari_accent_selection);

        Toolbar toolbar = findViewById(R.id.oldtoolbar);
        toolbar.setTitle(" Select a Qari Accent");
        toolbar.setNavigationIcon(R.drawable.back_nav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        qari1 = (CardView) findViewById(R.id.qari1);
        qari2 = (CardView) findViewById(R.id.qari2);
        qari1.setOnClickListener(this);
        qari2.setOnClickListener(this);
        intent = getIntent();

        if (intent.getStringExtra("level").equals("letters")) {
            nextIntent = new Intent(this,characters.class);

        } else if (intent.getStringExtra("level").equals("diacritics")) {
            nextIntent = new Intent(this,characters_diacritics.class);
        }else{
            nextIntent = new Intent(this,words.class);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.qari1){
            nextIntent.putExtra("qari","qari1");
            startActivity(nextIntent);
        }else{
            nextIntent.putExtra("qari","qari2");
            startActivity(nextIntent);

        }

    }
}
