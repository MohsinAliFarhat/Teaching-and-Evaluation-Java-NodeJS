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

public class characters extends AppCompatActivity {


    GridLayout mainGrid;
    MediaPlayer player ;
    int check =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);

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

        Intent intent = getIntent();
        if(intent.getStringExtra("qari").equals("qari2")){
            check =1;
        }

        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        player = null;
        setSingleEvent(mainGrid);

    }


    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);

            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    cardView.setCardBackgroundColor(Color.parseColor("#57FF22"));

                    if(check==0) {
                        playAudio(finalI);
                    }else{
                        playAudio2(finalI);
                    }



                }
            });
        }
    }



    private void playAudio(int x)
    {


        if(x == 0)
        {
            stopPlayer();


            if(player ==null)
            {

                player = MediaPlayer.create(this,R.raw.a00);
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
       /* if(x == 0)
        {
            Toast.makeText(MainActivity.this, "success  "+x, Toast.LENGTH_LONG).show();

        }*/

        else if(x == 1)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a01);
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
                player = MediaPlayer.create(this,R.raw.a02);
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
                player = MediaPlayer.create(this,R.raw.a03);
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
                player = MediaPlayer.create(this,R.raw.a04);
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
                player = MediaPlayer.create(this,R.raw.a05);
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
                player = MediaPlayer.create(this,R.raw.a06);
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
                player = MediaPlayer.create(this,R.raw.a07);
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
                player = MediaPlayer.create(this,R.raw.a08);
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
                player = MediaPlayer.create(this,R.raw.a09);
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
                player = MediaPlayer.create(this,R.raw.a010);
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
                player = MediaPlayer.create(this,R.raw.a011);
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
                player = MediaPlayer.create(this,R.raw.a012);
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
                player = MediaPlayer.create(this,R.raw.a013);
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
                player = MediaPlayer.create(this,R.raw.a014);
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
                player = MediaPlayer.create(this,R.raw.a015);
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
                player = MediaPlayer.create(this,R.raw.a016);
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
                player = MediaPlayer.create(this,R.raw.a017);
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
                player = MediaPlayer.create(this,R.raw.a018);
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
                player = MediaPlayer.create(this,R.raw.a019);
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

        else if(x == 20)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a020);
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

        else if(x == 21)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a021);
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

        else if(x == 22)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a022);
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

        else if(x == 23)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a023);
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

        else if(x == 24)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a024);
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

        else if(x == 25)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a025);
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

        else if(x == 26)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a026);
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

        else if(x == 27)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a027);
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

        else if(x == 28)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a028);
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
    private void playAudio2(int x)
    {
        //////////////////////////////////////////////////////// -- Code to add audios-- //////////////////////////////////////////////////////////////////////////////////////////////////


        if(x == 0)
        {
            stopPlayer();


            if(player ==null)
            {

                player = MediaPlayer.create(this,R.raw.a000);
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
       /* if(x == 0)
        {
            Toast.makeText(MainActivity.this, "success  "+x, Toast.LENGTH_LONG).show();

        }*/

        else if(x == 1)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a001);
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
                player = MediaPlayer.create(this,R.raw.a002);
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
                player = MediaPlayer.create(this,R.raw.a003);
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
                player = MediaPlayer.create(this,R.raw.a004);
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
                player = MediaPlayer.create(this,R.raw.a005);
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
                player = MediaPlayer.create(this,R.raw.a006);
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
                player = MediaPlayer.create(this,R.raw.a007);
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
                player = MediaPlayer.create(this,R.raw.a008);
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
                player = MediaPlayer.create(this,R.raw.a009);
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
                player = MediaPlayer.create(this,R.raw.a0010);
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
                player = MediaPlayer.create(this,R.raw.a0011);
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
                player = MediaPlayer.create(this,R.raw.a0012);
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
                player = MediaPlayer.create(this,R.raw.a0013);
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
                player = MediaPlayer.create(this,R.raw.a0014);
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
                player = MediaPlayer.create(this,R.raw.a0015);
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
                player = MediaPlayer.create(this,R.raw.a0016);
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
                player = MediaPlayer.create(this,R.raw.a0017);
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
                player = MediaPlayer.create(this,R.raw.a0018);
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
                player = MediaPlayer.create(this,R.raw.a0019);
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

        else if(x == 20)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a0020);
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

        else if(x == 21)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a0021);
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

        else if(x == 22)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a0022);
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

        else if(x == 23)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a0023);
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

        else if(x == 24)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a0024);
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

        else if(x == 25)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a0025);
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

        else if(x == 26)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a0026);
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

        else if(x == 27)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a0027);
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

        else if(x == 28)
        {
            stopPlayer();


            if(player ==null)
            {
                player = MediaPlayer.create(this,R.raw.a0028);
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
///////////////////////////////////////////-- Handling mediaPlayer////////////////////////////////////////////////////////////////////////////////

    private void setWhiteBackGround(){
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }
}
