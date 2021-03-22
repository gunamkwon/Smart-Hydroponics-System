package com.cookandroid.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TestFragment frag;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();
        frag = (TestFragment) manager.findFragmentById(R.id.frag);
    }

    public void mOnClick(View v) {
        switch(v.getId() ){
            case R.id.btn:
                TextView text = (TextView)frag.getView().findViewById(R.id.text_fragment);

                text.setText("Hello");
                break;
        }
    }
}

// https://kitesoft.tistory.com/83?category=549069