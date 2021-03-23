package com.cookandroid.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Fragment frag1,frag2,frag3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        frag1 = new AnalogFragment();
        frag2 = new DigitalFragment();
        frag3 = new CalenderFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag1).commit();


        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("DIGITAL CLOCK"));
        tabs.addTab(tabs.newTab().setText("ANALOG CLOCK"));
        tabs.addTab(tabs.newTab().setText("CALENDER"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment frselected = null;
                if (position == 0) {
                    frselected = frag1;
                } else if (position == 1) {
                    frselected = frag2;
                } else if (position == 2) {
                    frselected = frag3;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, frselected).commit();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab){
            }

        });

    }
}

// https://kitesoft.tistory.com/83?category=549069
// https://hijjang2.tistory.com/272?category=856483
//https://www.masterqna.com/android/53503/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-fragment%EC%97%90%EC%84%9C-%EB%B2%84%ED%8A%BC-%EC%9D%B4%EB%B2%A4%ED%8A%B8