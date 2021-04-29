package com.cookandroid.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

public class ControlFragment extends Fragment {

    private boolean ctrl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_control,null);
        Switch ctrl_switch = (Switch) view.findViewById(R.id.control_switch);
        ToggleButton autoled_btn = (ToggleButton) view.findViewById(R.id.led_switch);
        ToggleButton led_btn = (ToggleButton) view.findViewById(R.id.led_switch2);
        autoled_btn.setEnabled(false);
        led_btn.setEnabled(false);

        ctrl_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    autoled_btn.setEnabled(true);
                    if(ctrl)    led_btn.setEnabled(true);
                }
                else {
                    autoled_btn.setEnabled(false);
                    led_btn.setEnabled(false);
                }
            }
        });

        autoled_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    ctrl = isChecked;
                    ((MainActivity)getActivity()).sendData("1");
                    led_btn.setEnabled(true);
                }
                else {
                    ctrl = isChecked;
                    ((MainActivity)getActivity()).sendData("1");
                }
            }
        });

        led_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    ((MainActivity)getActivity()).sendData("2");
                }
                else {
                    ((MainActivity)getActivity()).sendData("M2");
                }
            }
        });

        return view;
    }
}
