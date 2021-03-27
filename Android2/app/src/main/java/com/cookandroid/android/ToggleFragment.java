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
        Button button1 = (Button)view.findViewById(R.id.plant_btn1);
        Button button2 = (Button)view.findViewById(R.id.plant_btn2);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).toggle_info(InfoFragment.newInstance());
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).toggle_info(SystemFragment.newInstance());
            }
        });

        return view;
    }
}
