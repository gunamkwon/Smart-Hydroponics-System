package com.cookandroid.android;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ToggleFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_toggle,null);
        Button btn_lettuce = (Button)view.findViewById(R.id.plant_btn1);
        Button btn_chives = (Button)view.findViewById(R.id.plant_btn2);
        Button btn_strawberry = (Button)view.findViewById(R.id.plant_btn3);

        btn_lettuce.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).toggle_info(LettuceFragment.newInstance());
                ((MainActivity)getActivity()).sendData("1#");
            }
        });

        btn_chives.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).toggle_info(ChivesFragment.newInstance());
                ((MainActivity)getActivity()).sendData("2#");
            }
        });

        btn_strawberry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).toggle_info(StrawberryFragment.newInstance());
                ((MainActivity)getActivity()).sendData("3#");
            }
        });

        return view;
    }
}
