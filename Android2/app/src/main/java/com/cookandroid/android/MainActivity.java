package com.cookandroid.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Fragment frag_info, frag_toggle,
            frag_sys, frag_ctrl,
            frag3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        frag_info = new InfoFragment();
        frag_toggle = new ToggleFragment();
        frag_sys = new SystemFragment();
        frag_ctrl = new ControlFragment();
        frag3 = new CalenderFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag_info)
                .replace(R.id.container_main, frag_toggle).commit();


        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("식물 선택"));
        tabs.addTab(tabs.newTab().setText("시스템 관리"));
        tabs.addTab(tabs.newTab().setText("연결 관리"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment frselected = null;
                Fragment frselected2 = null;
                if (position == 0) {
                    frselected = frag_info;
                    frselected2 = frag_toggle;
                } else if (position == 1) {
                    frselected = frag_sys;
                    frselected2 = frag_ctrl;
                } else if (position == 2) {
                    frselected = frag3;
                    frselected2 = frag_ctrl;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, frselected)
                        .replace(R.id.container_main, frselected2).commit();
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