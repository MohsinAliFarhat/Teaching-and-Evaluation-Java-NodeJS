package com.example.quran;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class characters_diacritics extends AppCompatActivity {


    GridLayout mainGrid;
    MediaPlayer player;
    int check =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters_diacritics);

        mainGrid = (GridLayout)findViewById(R.id.mainGrid);

        setSingleEvent(mainGrid);

        Toolbar toolbar =  findViewById(R.id.oldtoolbar);
        toolbar.setTitle("Characters with Diacritics");
        toolbar.setLogo(R.drawable.book_pic);
        toolbar.setNavigationIcon(R.drawable.back_nav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        if(intent.getStringExtra("qari").equals("qari2")){
            check =1;
        }

    }
    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
         final   CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    setWhiteBackGround();
                    cardView.setCardBackgroundColor(0xF1F1F1);
                if(check==0) {
                    playAudio(finalI);
                }else{
                    playAudio1(finalI);
}


                }
            });
        }
    }

/////////////////////////////////////////////////-- Handling mediaPlayer////////////////////////////////////////////////////////////////////////////////



    private void playAudio(int x)
    {
        //////////////////////////////////////////////////////// -- Code to add audios-- //////////////////////////////////////////////////////////////////////////////////////////////////


        if(x == 0)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c00);
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


        else if(x == 1)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c01);
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
        //////////////////////////////////////////2_12_19 Change/////////////////////////////////////////////////////////////////

        else if(x == 2)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c02);
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

        else if(x == 3)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c03);
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

        else if(x == 4)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c04);
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

        else if(x == 5)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c05);
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

        else if(x == 6)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c06);
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

        else if(x == 7)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c07);
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

        else if(x == 8)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c08);
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

        else if(x == 9)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c09);
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

        else if(x == 10)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c10);
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

        else if(x == 11)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c11);
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

        else if(x == 12)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c12);
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

        else if(x == 13)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c13);
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

        else if(x == 14)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c14);
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

        else if(x == 15)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c15);
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

        else if(x == 16)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c16);
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

        else if(x == 17)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c17);
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

        else if(x == 18)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c18);
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

        else if(x == 19)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c19);
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



        else
        {
            Toast.makeText(this, "Set action for this card  ", Toast.LENGTH_LONG).show();

        }

//////////////////////////////////////////////////////// -- Code to add audios-- ///////////////////////////////////////////////////////////////////////////


    }
    private void playAudio1(int x)
    {
        //////////////////////////////////////////////////////// -- Code to add audios-- //////////////////////////////////////////////////////////////////////////////////////////////////


        if(x == 0)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c000);
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


        else if(x == 1)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c001);
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
        //////////////////////////////////////////2_12_19 Change/////////////////////////////////////////////////////////////////

        else if(x == 2)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c002);
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

        else if(x == 3)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c003);
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

        else if(x == 4)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c004);
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

        else if(x == 5)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c005);
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

        else if(x == 6)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c006);
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

        else if(x == 7)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c007);
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

        else if(x == 8)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c008);
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

        else if(x == 9)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c009);
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

        else if(x == 10)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c010);
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

        else if(x == 11)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c011);
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

        else if(x == 12)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c012);
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

        else if(x == 13)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c013);
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

        else if(x == 14)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c014);
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

        else if(x == 15)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c015);
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

        else if(x == 16)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c016);
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

        else if(x == 17)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c017);
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

        else if(x == 18)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c018);
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

        else if(x == 19)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.c019);
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



        else
        {
            Toast.makeText(this, "Set action for this card  ", Toast.LENGTH_LONG).show();

        }

//////////////////////////////////////////////////////// -- Code to add audios-- ///////////////////////////////////////////////////////////////////////////


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
    private void setWhiteBackGround(){
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }
}
