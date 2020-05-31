package com.example.quran;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class tajweed_rules extends AppCompatActivity {

    ViewPager vp = null;
    LinearLayout dotsLayout = null;
    SliderAdapter adapter;
    TextView[] dots = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar =  findViewById(R.id.oldtoolbar);
        toolbar.setTitle("  Tajweed Rules");
        toolbar.setLogo(R.drawable.rules);
        toolbar.setNavigationIcon(R.drawable.back_nav);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        vp = (ViewPager) findViewById(R.id.viewPager);
        dotsLayout= (LinearLayout) findViewById(R.id.dots);

        adapter = new SliderAdapter(this);

        vp.setAdapter(adapter);
        Dots(0);

        vp.addOnPageChangeListener(viewListener);

    }

    public void Dots(int position){
        dots = new  TextView[5];
        dotsLayout.removeAllViews();
        for(int i=0 ; i < dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#000000"));

            dotsLayout.addView(dots[i]);
        }

        if(dots.length  > 0 ){
            dots[position].setTextColor(Color.parseColor("#50A708"));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.SimpleOnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageSelected(int position) {
            Dots(position);

        }
    };

}
