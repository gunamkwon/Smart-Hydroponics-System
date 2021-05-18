package com.cookandroid.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class SystemFragment extends Fragment {

    static int green, red;
    public static TextView data1,level,connect;
    View view;

    public static SystemFragment newInstance() {
        return new SystemFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_system, container, false);
        connect = (TextView) view.findViewById(R.id.connect);
        data1 = (TextView) view.findViewById(R.id.data_water);
        level = (TextView) view.findViewById(R.id.level_state);

        green = getResources().getColor(R.color.green);
        red = getResources().getColor(R.color.red);

        return view;
    }

    public static void setData(String data)
    {
        data1.setText(data);
    }

    public static void setLevelState(String state)
    {
        if(state == "Enough") {
            SystemFragment.level.setText(state);
            SystemFragment.level.setTextSize(23);
            SystemFragment.level.setTextColor(green);
        }
        else {
            SystemFragment.level.setText(state);
            SystemFragment.level.setTextSize(18);
            SystemFragment.level.setTextColor(red);
        }
    }

    public static void setConnectState(int state)
    {
        if(state == 1) {
            connect.setText("Connected");
            connect.setTextSize(18);
            connect.setTextColor(green);
        }
        else {
            connect.setText("Unconnected");
            connect.setTextSize(15);
            connect.setTextColor(red);
        }
    }
}
