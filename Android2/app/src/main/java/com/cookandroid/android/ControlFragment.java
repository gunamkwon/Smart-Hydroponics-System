package com.cookandroid.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ControlFragment extends Fragment {

    private int ctrl = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_control,null);
        Switch ctrl_switch = (Switch) view.findViewById(R.id.control_switch);
        Switch led_switch = (Switch) view.findViewById(R.id.led_switch);
        Switch led_switch2 = (Switch) view.findViewById(R.id.led_switch2);
        Switch led_switch3 = (Switch) view.findViewById(R.id.led_switch3);
        led_switch.setEnabled(false);
        led_switch2.setEnabled(false);
        led_switch3.setEnabled(false);

        ctrl_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    ctrl = 1;
                    led_switch.setEnabled(true);
                    led_switch2.setEnabled(true);
                    led_switch3.setEnabled(true);
                }
                else {
                    ctrl = 0;
                    led_switch.setEnabled(false);
                    led_switch2.setEnabled(false);
                    led_switch3.setEnabled(false);
                }
            }
        });

        led_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked && ctrl == 1) {
                    ((MainActivity)getActivity()).sendData("1");
                }
                else if(isChecked && ctrl == 0 ) {
                    Toast.makeText(getActivity(),"원격제어를 활성화하십시오.",Toast.LENGTH_LONG).show();
                }
            }
        });

        led_switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked && ctrl == 1) {
                    ((MainActivity)getActivity()).sendData("2");
                }
                else if(isChecked && ctrl == 0 ) {
                    Toast.makeText(getActivity(),"원격제어를 활성화하십시오.",Toast.LENGTH_LONG).show();
                }
            }
        });

        led_switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked && ctrl == 1) {
                    ((MainActivity)getActivity()).sendData("3");
                }
                else if(isChecked && ctrl == 0 ) {
                    Toast.makeText(getActivity(),"원격제어를 활성화하십시오.",Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
