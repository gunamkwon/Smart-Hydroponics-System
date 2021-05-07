package com.cookandroid.android;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class SystemFragment extends Fragment {

    static int green, red;
    private static TextView data1;
    private static TextView level;
    private static TextView connect;

    public static SystemFragment newInstance() {
        return new SystemFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_system,null);
        data1 = (TextView) view.findViewById(R.id.data_water);
        connect = (TextView) view.findViewById(R.id.connect_state);
        level = (TextView) view.findViewById(R.id.level_state);
        green = getResources().getColor(R.color.green);
        red = getResources().getColor(R.color.red);
        return view;

    }

    public static void setData(String data)
    {
        SystemFragment.data1.setText(data);
    }

    public static void setLevelState(String state)
    {
        if(state == "1") {
            SystemFragment.level.setText("Enough");
            SystemFragment.connect.setTextSize(23);
            SystemFragment.connect.setTextColor(green);
        }
        else {
            SystemFragment.level.setText("Shortage");
            SystemFragment.connect.setTextSize(18);
            SystemFragment.connect.setTextColor(red);
        }
    }

    public static void setConnectState(int state)
    {
        if(state == 1) {
            SystemFragment.connect.setText("Connected");
            SystemFragment.connect.setTextSize(18);
            SystemFragment.connect.setTextColor(green);
        }
        else {
            SystemFragment.connect.setText("UnConnected");
            SystemFragment.connect.setTextSize(15);
            SystemFragment.connect.setTextColor(red);
        }
    }


}
