package com.cookandroid.android;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class ConnectionFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = (View) inflater.inflate(R.layout.fragment_connection,null);
        Button bt_on = (Button) view.findViewById(R.id.btn_btON);
        Button bt_off = (Button) view.findViewById(R.id.btn_btOFF);
        Button btn_list = (Button) view.findViewById(R.id.btn_list);
        bt_on.setOnClickListener(this);
        bt_off.setOnClickListener(this);
        btn_list.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_btON:
                ((MainActivity)getActivity()).bluetoothON();
                break;
            case R.id.btn_btOFF:
                ((MainActivity)getActivity()).bluetoothOFF();
                break;
            case R.id.btn_list:
                ((MainActivity)getActivity()).listPairedDevices();
                break;
        }
    }
}