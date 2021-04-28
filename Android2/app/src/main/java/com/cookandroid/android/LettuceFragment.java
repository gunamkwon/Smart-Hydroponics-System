package com.cookandroid.android;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LettuceFragment extends Fragment {

    public static LettuceFragment newInstance() {
        return new LettuceFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_lettuce,null);

        TextView text = (TextView) view.findViewById(R.id.lettuce_text);
        text.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
}
