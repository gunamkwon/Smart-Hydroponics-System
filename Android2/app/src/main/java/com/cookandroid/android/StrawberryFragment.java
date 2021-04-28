package com.cookandroid.android;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class StrawberryFragment extends Fragment {
    public static StrawberryFragment newInstance() {return new StrawberryFragment();}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_strawberry,null);

        TextView text = (TextView) view.findViewById(R.id.strawberrytext);
        text.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }
}
