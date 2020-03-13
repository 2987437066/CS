package com.example.day01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.day01.bean.ResultsBean;
import com.example.day01.presenter.NetPersenter;
import com.example.day01.view.NetView;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements NetView {

    private ViewPager mVp;
    private List<ResultsBean> list;
    private List<View> list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        NetPersenter netPersenter = new NetPersenter(this);
        netPersenter.getData();
    }

    private void initView() {
        mVp = findViewById(R.id.vp);
        list1 = new ArrayList<>();
        list = new ArrayList<>();
    }

    @Override
    public void setData(List<ResultsBean> resultsBeans) {
        list.addAll(resultsBeans);
        for (int i = 0; i < list.size(); i++) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.item_img, null);
            PhotoView iv = inflate.findViewById(R.id.iv);
            Glide.with(this).load(list.get(i).getUrl()).into(iv);
            list1.add(inflate);
        }
        MyPageadapter pageadapter = new MyPageadapter(list1);
        mVp.setAdapter(pageadapter);
        Intent intent = getIntent();
        String poi = intent.getStringExtra("poi");
        int i = Integer.parseInt(poi);
        mVp.setCurrentItem(i);
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(this,str, Toast.LENGTH_SHORT).show();
    }
}
