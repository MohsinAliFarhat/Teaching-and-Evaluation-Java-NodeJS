package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class learn_supplication extends AppCompatActivity implements View.OnClickListener {

    CardView dua1 = null;
    CardView dua2 = null;
    CardView dua3 = null;
    CardView dua4 = null;
    CardView dua5 = null;
    CardView dua6 = null;
    CardView dua7 = null;
    CardView dua8 = null;
    CardView dua9 = null;
    CardView dua10 = null;
    CardView dua11 = null;
    Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_supplication);

        Toolbar toolbar =  findViewById(R.id.oldtoolbar);
        toolbar.setTitle("Learn Supplications");;
        toolbar.setNavigationIcon(R.drawable.back_nav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        intent = new Intent(this, supplication.class);
        dua1 = (CardView)findViewById(R.id.dua1);
        dua2 = (CardView)findViewById(R.id.dua2);
        dua3 = (CardView)findViewById(R.id.dua3);
        dua4 = (CardView)findViewById(R.id.dua4);
        dua5 = (CardView)findViewById(R.id.dua5);
        dua6 = (CardView)findViewById(R.id.dua6);
        dua7 = (CardView)findViewById(R.id.dua7);
        dua8 = (CardView)findViewById(R.id.dua8);
        dua9 = (CardView)findViewById(R.id.dua9);
        dua10 = (CardView)findViewById(R.id.dua10);
        dua11 = (CardView)findViewById(R.id.dua11);

        dua1.setOnClickListener(this);
        dua2.setOnClickListener(this);
        dua3.setOnClickListener(this);
        dua4.setOnClickListener(this);
        dua5.setOnClickListener(this);
        dua6.setOnClickListener(this);
        dua7.setOnClickListener(this);
        dua8.setOnClickListener(this);
        dua9.setOnClickListener(this);
        dua10.setOnClickListener(this);
        dua11.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.dua1){
            intent.putExtra("dua","Finishing Meal");
            intent.putExtra("arabic"," الْحَمْدُ لِلَّهِ الَّذِى اطْعَمَنَا وَسَقَانَا ، وَجَعَلنَا مُسْلِمِينَِ");
            intent.putExtra("translation","Meaning: Praise be to Allah Who has fed us and given us drink and made us Muslims");

        }
        else if(v.getId()==R.id.dua2){
            intent.putExtra("dua","Entering Toilet");
            intent.putExtra("arabic","  اللَّهُـمَّ إِنِّي أَعُـوذُ بِـكَ مِـنَ الْخُـبْثِ وَالْخَبَائِثِِْ");
            intent.putExtra("translation","Meaning: O Allah, I take refuge with you from all evil and evil-doers");

        }
        else if(v.getId()==R.id.dua3){
            intent.putExtra("dua","Leaving Toilet");
            intent.putExtra("arabic"," غُفْرَانَكَ");
            intent.putExtra("translation","Meaning:  I ask You (Allah) for forgiveness");

        }
        else if(v.getId()==R.id.dua4){
            intent.putExtra("dua","After Rainfall");
            intent.putExtra("arabic"," مُطِرْنَا بِفَضْلِ اللهِ وَ رَحْمَتِهِ");
            intent.putExtra("translation","Meaning: We have been given rain by the grace and mercy of Allah");

        }
        else if(v.getId()==R.id.dua5){
            intent.putExtra("dua","End Of Gathering");
            intent.putExtra("arabic"," سُبْحـانَكَ اللّهُـمَّ وَبِحَمدِك، أَشْهَـدُ أَنْ لا إِلهَ إِلاّ أَنْتَ أَسْتَغْفِرُكَ وَأَتوبُ إِلَـيْكِ");
            intent.putExtra("translation","Meaning:  Glory is to You, O Allah, and praise is to You. I bear witness that there is none worthy of worship but You. I seek Your forgiveness and repent to You");

        }
        else if(v.getId()==R.id.dua6){
            intent.putExtra("dua","Before Sleeping");
            intent.putExtra("arabic"," اللَّهُمَّ بِاسْـمِكَ أَمُوتُ وَأَحْيَا");
            intent.putExtra("translation","Meaning: In Your name O Allah, I live and die");

        }
        else if(v.getId()==R.id.dua7){
            intent.putExtra("dua","Starting anything");
            intent.putExtra("arabic"," بِسْمِ اللّٰہِ الرَّحْمٰنِ الرَّحِيْمِ");
            intent.putExtra("translation","Meaning: In the name of Allah , the Entirely Merciful, the Especially Merciful");

        }
        else if(v.getId()==R.id.dua8){
            intent.putExtra("dua","During Windstorm");
            intent.putExtra("arabic"," اللّهُـمَّ إِنَّـي أَسْـأَلُـكَ خَيْـرَها، وَأَعـوذُ بِكَ مِنْ شَـرِّها");
            intent.putExtra("translation","Meaning:  O Allah , I ask You for the good of it and seek refuge in You against its evil");

        }
        else if(v.getId()==R.id.dua9){
            intent.putExtra("dua","Forgiveness");
            intent.putExtra("arabic"," رَبِّ اغْفِرْ لِي رَبِّ اغْفِرْ لِيِ");
            intent.putExtra("translation","Meaning: Lord, forgive me. My Lord, forgive me");

        }
        else if(v.getId()==R.id.dua10){
            intent.putExtra("dua","Jannah");
            intent.putExtra("arabic"," رَبَّنَا اصْرِفْ عَنَّا عَذَابَ جَهَنَّمَ ۖ إِنَّ عَذَابَهَا كَانَ غَرَامًا إِنَّهَا سَاءَتْ مُسْتَقَرًّا وَمُقَامًاِ");
            intent.putExtra("translation","Meaning:  Our Lord! Avert from us the Wrath of Hell, for its Wrath is indeed an affliction grievous,- Evil indeed is it as an abode, and as a place to rest in");

        }
        else if(v.getId()==R.id.dua11){
            intent.putExtra("dua","For Rizq");
            intent.putExtra("arabic"," اللَّهُمَّ إِنِّي أَسْأَلُكَ عِلْماً نَافِعاً، وَرِزْقاً طَيِّباً، وَعَمَلاً مُتَقَبَّلاِ");
            intent.putExtra("translation","Meaning:  O Allah, I ask You for knowledge that is of benefit , a good provision , and deeds that will be accepted");

        }
        startActivity(intent);
    }
}
