package com.cookandroid.android;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ChivesFragment extends Fragment {
    public static ChivesFragment newInstance() { return new ChivesFragment(); }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_chives,null);

        TextView text = (TextView) view.findViewById(R.id.chives_text);
        text.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
}
