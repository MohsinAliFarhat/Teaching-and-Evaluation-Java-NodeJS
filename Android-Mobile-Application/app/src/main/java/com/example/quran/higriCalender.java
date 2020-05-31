package com.example.quran;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class higriCalender extends AppCompatActivity {

    TextView tv = null;
    TextView tv2 = null;
    CalendarView cv = null;
    int year=0;
    int month =0;
    int day = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higri_calender);

        Toolbar toolbar =  findViewById(R.id.oldtoolbar);
        toolbar.setTitle("  Islamic Calender");
        toolbar.setLogo(R.drawable.calen);
        toolbar.setNavigationIcon(R.drawable.back_nav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.convert);


        cv = (CalendarView) findViewById(R.id.calendarView);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                year = i;
                month = i1;
                day= i2;
                tv2.setText(DateHigri.writeIslamicDate1(i,i1,i2));
            }
        });


        tv.setText(("Today:\n"+ DateHigri.writeIslamicDate()));

//        Toast.makeText(this,""+    DateHigri.writeIslamicDate(),Toast.LENGTH_LONG).show();


    }
}
