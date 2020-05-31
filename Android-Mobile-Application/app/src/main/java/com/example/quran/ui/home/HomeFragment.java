package com.example.quran.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.quran.R;
import com.example.quran.higriCalender;
import com.example.quran.learn_kalimas;
import com.example.quran.learn_supplication;
import com.example.quran.level_selection;
import com.example.quran.level_selection_evaluation;
import com.example.quran.tajweed_rules;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;

    Button tajweed = null;
    Button islamicCalender = null;
    Button learn = null;
    Button evaluate = null;
    Button kalimas = null;
    Button supplication = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);




        tajweed = (Button)root.findViewById(R.id.tajweed);
        islamicCalender = (Button) root.findViewById(R.id.calender);
        kalimas  = (Button)root.findViewById(R.id.kalimas);
        learn = (Button) root.findViewById(R.id.learn);
        evaluate = (Button) root.findViewById(R.id.evaluate);
        supplication = (Button) root.findViewById(R.id.supplication);





        tajweed.setOnClickListener(this);
        islamicCalender.setOnClickListener(this);
        learn.setOnClickListener(this);
        evaluate.setOnClickListener(this);
        kalimas.setOnClickListener(this);
        supplication.setOnClickListener(this);



        return root;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.tajweed){

            Intent intent = new Intent(getActivity(), tajweed_rules.class);
            startActivity(intent);
        }

        else if(view.getId()==R.id.calender){

            Intent intent = new Intent(getActivity(), higriCalender.class);
            startActivity(intent);

        }

        else if(view.getId()==R.id.learn){
            Intent intent = new Intent(getActivity(), level_selection.class);
            startActivity(intent);

        }
        else if(view.getId()==R.id.evaluate){

            Intent intent = new Intent(getActivity(), level_selection_evaluation.class);
            startActivity(intent);

        }        else if(view.getId()==R.id.kalimas){

            Intent intent = new Intent(getActivity(), learn_kalimas.class);
            startActivity(intent);

        }
        else if(view.getId()==R.id.supplication){

            Intent intent = new Intent(getActivity(), learn_supplication.class);
            startActivity(intent);

        }
    }
}