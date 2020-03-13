package com.example.day01;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.day01.bean.ResultsBean;
import com.example.day01.fragments.AFragment;
import com.example.day01.fragments.BFragment;
import com.example.day01.presenter.NetPersenter;
import com.example.day01.view.NetView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        final AFragment aFragment = new AFragment();
        final BFragment bFragment = new BFragment();
        fragments.add(aFragment);
        fragments.add(bFragment);
        final FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction()
                .add(R.id.fl, aFragment)
                .add(R.id.fl, bFragment)
                .hide(bFragment)
                .commit();
        mTbl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        supportFragmentManager.beginTransaction().show(aFragment).hide(bFragment).commit();
                        break;
                    case 1:
                        supportFragmentManager.beginTransaction().show(bFragment).hide(aFragment).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTbl.addTab(mTbl.newTab().setText("主页").setIcon(R.drawable.select));
        mTbl.addTab(mTbl.newTab().setText("收藏").setIcon(R.drawable.select));
    }

    private void initView() {
        mTbl = findViewById(R.id.tbl);
    }
}
