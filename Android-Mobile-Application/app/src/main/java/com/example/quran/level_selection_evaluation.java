package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class level_selection_evaluation extends AppCompatActivity implements View.OnClickListener {


    Button btn1 = null;
    Button btn2 = null;
    Button btn3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level__select_evaluation);

        Toolbar toolbar =  findViewById(R.id.oldtoolbar);
        toolbar.setTitle("  Select a level");
        toolbar.setLogo(R.drawable.brain);
        toolbar.setNavigationIcon(R.drawable.back_nav);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        btn1= (Button) findViewById(R.id.words);
        btn2 = (Button)findViewById(R.id.diacritics);
        btn3 = (Button)findViewById(R.id.letter);
        btn3.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.words){
            Intent intent = new Intent(this, words_evaluation.class);
            startActivity(intent);
        }
        else if(view.getId()==R.id.diacritics){

            Intent intent = new Intent(this,characters_diacritics_evaluation.class);
            startActivity(intent);

        }else if(view.getId()==R.id.letter){
            Intent intent = new Intent(this,characters_evaluation.class);
            startActivity(intent);

        }
    }
}
