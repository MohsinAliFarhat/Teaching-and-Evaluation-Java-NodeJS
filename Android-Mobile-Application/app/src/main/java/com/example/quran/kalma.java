package com.example.quran;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class kalma extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer player;
    ImageButton play,stop,pause;
    TextView arabic = null;
    TextView translation = null;
    Intent intent = null;
    int audio = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalma);

        arabic = (TextView)findViewById(R.id.arabic);
        translation = (TextView)findViewById(R.id.translation);
        play = (ImageButton) findViewById(R.id.imageButton_play);
        stop = (ImageButton) findViewById(R.id.imageButton_stop);
        pause = (ImageButton) findViewById(R.id.imgBtn_pause);

        intent = getIntent();
        arabic.setText(intent.getStringExtra("arabic"));
        translation.setText(intent.getStringExtra("translation"));


        Toolbar toolbar =  findViewById(R.id.oldtoolbar);
        toolbar.setTitle(intent.getStringExtra("kalma")+" Kalma");;
        toolbar.setNavigationIcon(R.drawable.back_nav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        pause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == play.getId())
        {

            if(intent.getStringExtra("kalma").equals("first")){
                audio = R.raw.kalma_1;
            }else if(intent.getStringExtra("kalma").equals("second")){
                audio = R.raw.kalma_2;
            }else if(intent.getStringExtra("kalma").equals("third")){
                audio = R.raw.kalma_3;
            }else if(intent.getStringExtra("kalma").equals("fourth")){
                audio = R.raw.kalma_4;
            }else if(intent.getStringExtra("kalma").equals("fifth")){
                audio = R.raw.kalma_5;
            }else if(intent.getStringExtra("kalma").equals("sixth")){
                audio = R.raw.kalma_6;
            }
            playAudio(audio);
        }




        if(v.getId() == stop.getId())
        {

            stopPlayer();

        }

        if(v.getId() == pause.getId())
        {

            pause();

        }


}

    private void playAudio(int audio)
    {



        if(player ==null)
        {
            player = MediaPlayer.create(this,audio);
            // player = MediaPlayer.create(MainActivity.this,R.raw.c00);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {
                @Override
                public void onCompletion(MediaPlayer mp)
                {
                    stopPlayer();
                }
            });
        }
        player.start();

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

    private void pause()
    {
        if(player != null)
        {
            player.pause();

        }
    }


    @Override
    protected void onStop()
    {
        super.onStop();
        stopPlayer();
    }}



