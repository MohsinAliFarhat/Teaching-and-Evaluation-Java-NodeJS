package com.example.quran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;


public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;


    public SliderAdapter(Context context){

        this.context = context;

    }


    public int[]  slide_images = {

            R.drawable.a001,
            R.drawable.arabic_letters,
            R.drawable.silent_letters
            , R.drawable._tanween,
            R.drawable.a001
    };


    public String[] slide_headings = {

      "What is Tajweed ?",
            "   Arabic Letters",
            "  Silent Letters",
            "      Tanween",
           "Etiquettes of The\n      Holy Quran"

    };


    public String[] slide_descs = {
      "Tajweed means to make beauty in reading.\n" +
              "\n" +
              "It means to pronounce every letter correctly with all its qualities\n" +
              "\n" +
              "\n" +
              "Almighty Allah Says in the Holy Quran that::\n" +
              "\n" +
              "\"Recite hte Holy Quran slowly and making the letters clear\"",
            "There are total of 30 Arabic letters given below.",
            "A letter which is free from any Harkat is called a silent letter. This letter will be written but not read."
            ,
            "1. Two Fathah, two Kasrah and Two dammas are known as Tanween.\n" +
                    "2. The sound of a Noon sakin and Tanween is the same\n" +
                    "3. Two fathas, two kasras or two dammas that together give the sound of Noon  ",
            "\n1. To Make a Wadu/Perform ablution first because Allah loves those who clean\n themselves.\n\n" +
                    "2. To sit properly / with great respect\n\n" +
                    "3. Not to talk or play while reading Lesson/Quran.\n\n"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }



    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =  layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideimage = (ImageView) view.findViewById(R.id.imageView2);
        TextView slideHeading = (TextView) view.findViewById(R.id.tv1);
        TextView slidedescription= (TextView) view.findViewById(R.id.tv2);



        if(slide_images[position]==R.drawable.a001){
            slideimage.setAlpha(0);
        }else{

            slideimage.setImageResource(slide_images[position]);
        }

        slideHeading.setText(slide_headings[position]);
        slidedescription.setText(slide_descs[position]);

        container.addView(view);

        return  view;

    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);


    }

}
