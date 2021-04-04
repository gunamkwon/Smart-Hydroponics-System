package com.cookandroid.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

public class ControlFragment extends Fragment {

    private int ctrl = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_control,null);
        Switch ctrl_switch = (Switch) view.findViewById(R.id.control_switch);
        Switch led_switch = (Switch) view.findViewById(R.id.led_switch);


        ctrl_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    ctrl = 1;
                }
                else {
                    ctrl = 0;
                }
            }
        });

        led_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked && ctrl == 1) {
                    MainActivity.sendData("1");
                }
                else if(isChecked && ctrl == 0 ) {
                    led_switch.toggle();
                }
            }
        });


        return view;
    }
}
