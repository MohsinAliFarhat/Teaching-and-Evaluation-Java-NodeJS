package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class learn_kalimas extends AppCompatActivity implements View.OnClickListener {

    CardView first = null;
    CardView  second= null;
    CardView  third= null;
    CardView  fourth= null;
    CardView  fifth= null;
    CardView sixth = null;
    Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lear_kalimas);

        Toolbar toolbar =  findViewById(R.id.oldtoolbar);
        toolbar.setTitle("Learn Kalimas");;
        toolbar.setNavigationIcon(R.drawable.back_nav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

       first = (CardView)findViewById(R.id.firstk);
       second = (CardView)findViewById(R.id.secondk);
       third  = (CardView)findViewById(R.id.thirdk);
       fourth = (CardView)findViewById(R.id.fourk);
       fifth = (CardView)findViewById(R.id.fivek);
       sixth = (CardView)findViewById(R.id.sixthk);
       intent = new Intent(this,kalma.class);

       first.setOnClickListener(this);
       second.setOnClickListener(this);
       third.setOnClickListener(this);
       fourth.setOnClickListener(this);
       fifth.setOnClickListener(this);
       sixth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.firstk){
            intent.putExtra("kalma","first");
            intent.putExtra("arabic"," لآ اِلَهَ اِلّا اللّهُ مُحَمَّدٌ رَسُوُل اللّهِ");
            intent.putExtra("translation","Meaning: There is no God but Allah Muhammad is the Messenger of Allah");

        }else if(v.getId()==R.id.secondk){
            intent.putExtra("kalma","second");
            intent.putExtra("arabic","اشْهَدُ انْ لّآ اِلهَ اِلَّا اللّهُ وَحْدَه لَا شَرِيْكَ لَه، وَ اَشْهَدُ اَنَّ مُحَمَّدً اعَبْدُهوَرَسُولُه");
            intent.putExtra("translation","Meaning:  I bear witness that no-one is worthy of worship but Allah, the One alone, without partner, and I bear witness that Muhammad is His servant and Messenger");

        }else if(v.getId()==R.id.thirdk){
            intent.putExtra("kalma","third");
            intent.putExtra("arabic","سُبْحَان لِلّه وَ الْحَمْدُ   لِلّهِ وَ لآ اِلهَ اِلّا اللّهُ، وَ اللّهُ اَكْبَرُ وَلا حَوْلَ وَلاَ قُوَّة  ِلَّا بِاللّهِ الْعَلِىّ الْعَظِيْم");
            intent.putExtra("translation","Meaning: Glory be to Allah and Praise to Allah, and there is no God But Allah, and Allah is the Greatest. And there is no Might or Power except with Allah");

        }else if(v.getId()==R.id.fourk){
            intent.putExtra("kalma","fourth");
            intent.putExtra("arabic","ا الهَ اِلَّا اللّهُ وَحْدَهُ لا شَرِيْكَ لَهْ، لَهُ الْمُلْكُ وَ لَهُ الْحَمْدُ يُحْى وَ يُمِيْتُ وَ هُوَحَىُّ لَّا يَمُوْتُ اَبَدًا اَبَدًا طذُو الْجَلَالِ وَ الْاِكْرَامِ ط بِيَدِهِ الْخَيْرُ ط وَهُوَ عَلى كُلِّ شَئ ٍ قَدِيْرٌ");
            intent.putExtra("translation","Meaning: There is) none worthy of worship except Allah. He is only One. (There is) no partners for Him. For Him (is) the Kingdom. And for Him (is) the Praise. He gives life and acuses death. And He (is) Alive. He will not die, never, ever. Possessor of Majesty and Reverence. In His hand (is) the goodness. And He (is) the goodness. And He (is) on everything powerful..");

        }else if(v.getId()==R.id.fivek){
            intent.putExtra("kalma","fifth");
            intent.putExtra("arabic","اسْتَغْفِرُ اللّهَ رَبِّىْ مِنْ كُلِّ ذَنْبٍ اَذْنَبْتُه عَمَدًا اَوْ خَطَاً سِرًّا اَوْ عَلَانِيَةً وَاَتُوْبُ اِلَيْهِ مِنْ الذَّنْبِ الَّذِىْ اَعْلَمُ وَ مِنْ الذَّنْبِ الَّذِىْ لا اَعْلَمُ اِنَّكَ اَنْتَ عَلَّامُ الغُيُبِ وَ سَتَّارُ الْعُيُوْبِ و َغَفَّارُ الذُّنُوْبِ وَ لا حَوْلَ وَلا قُوَّةَ اِلَّا بِاللّهِ الْعَلِىِّ العَظِيْم");
            intent.putExtra("translation","Meaning: I seek forgiveness from Allah, my Lord, from every sin I committed knowingly or unknowingly, secretly or openly, and I turn towards Him from the sin that I know and from the sin that I do not know. Certainly You, You (are) the knower of the hidden things and the Concealer (of) the mistakes and the Forgiver (of) the sins. And (there is) no power and no strength except from Allah, the Most High, the Most Great.");

        }else if(v.getId()==R.id.sixthk){
            intent.putExtra("kalma","sixth");
            intent.putExtra("arabic"," اَللّٰهُمَّ اِنِّیْٓ اَعُوْذُ بِكَ مِنْ اَنْ اُشْرِكَ بِكَ شَيْئًا وَّاَنَآ اَعْلَمُ بِهٖ وَ اَسْتَغْفِرُكَ لِمَا لَآ اَعْلَمُ بِهٖ تُبْتُ عَنْهُ وَ تَبَرَّأْتُ مِنَ الْكُفْرِ وَ الشِّرْكِ وَ الْكِذْبِ وَ الْغِيْبَةِ وَ الْبِدْعَةِ وَ النَّمِيْمَةِ وَ الْفَوَاحِشِ وَ الْبُهْتَانِ وَ الْمَعَاصِىْ كُلِّهَا وَ اَسْلَمْتُ وَ اَقُوْلُ لَآ اِلٰهَ اِلَّا اللهُ مُحَمَّدٌ رَّسُوْلُ اللهِؕ");
            intent.putExtra("translation","Meaning: O Allah! Certainly I seek protection with You from, that I associate partner with You anything and I know it. And I seek forgiveness from You for that I do not know it. I repended from it and I made myself free from disbelief and polytheism and the falsehood and the back-biting and the innovation and the tell-tales and the bad deeds and the blame and the disobedience, all of them. And I submit and I say (there is) none worthy of worship except Allah, Muhammad is the Messenger of Allah.");

        }
        startActivity(intent);

    }
}
