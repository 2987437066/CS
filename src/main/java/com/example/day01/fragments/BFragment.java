package com.example.day01.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.day01.BaseApp;
import com.example.day01.MyBAdapter;
import com.example.day01.R;
import com.example.day01.bean.ResultsBean;
import com.example.xts.greendaodemo.db.ResultsBeanDao;

import java.util.ArrayList;
import java.util.List;

public class BFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mRlvs;
    private CheckBox mCb;
    private Button mBtDelete;
    private MyBAdapter adapter;
    private List<ResultsBean> list;
    private ResultsBeanDao dao;
    private int p;
    private TextView mTv;
    private int a;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.item_recs, null);
        iniitView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        mRlvs.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlvs.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        dao = BaseApp.getInstance().getDaoSession().getResultsBeanDao();
        list = dao.loadAll();
        adapter = new MyBAdapter(list, getContext());
        mRlvs.setAdapter(adapter);
        adapter.setOnItemClickListenenr(new MyBAdapter.onItemClickListenenr() {
            @Override
            public void onItemClick(int pi) {
                p = pi;
                boolean select = list.get(pi).getIsSelect();
                if (select){
                    a++;
                    mTv.setText(a+"/"+list.size());
                }else {
                    a--;
                    if (a == 0){
                        mCb.setChecked(false);
                    }
                    mTv.setText(a+"/"+list.size());
                }
                if (a == list.size()){
                    mCb.setChecked(true);
                }
            }
        });
        if (list == null){
            mTv.setText("0"+"/"+"0");
        }else {
            mTv.setText("0"+"/"+list.size());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        initData();
    }

    private void iniitView(View inflate) {
        mRlvs = inflate.findViewById(R.id.rlvs);
        mCb = inflate.findViewById(R.id.cb);
        mBtDelete = inflate.findViewById(R.id.bt_delete);
        mTv = inflate.findViewById(R.id.tv);
        mBtDelete.setOnClickListener(this);
        mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCb.isChecked()) {
                    for (int i = 0; i < list.size(); i++) {
                        mTv.setText(list.size()+"/"+list.size());
                        mCb.setText("全选");
                        list.get(i).isSelect = true;
                    }
                    adapter.setSelectAll(true);
                } else {
                    a = 0;
                    mTv.setText("0"+"/"+list.size());
                    mCb.setText("取消");
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).isSelect = false;
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_delete:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isSelect) {
                        dao.deleteInTx(list.get(p));
                        list.remove(i);
                        i--;
                    }
                }
                mTv.setText("0"+"/"+list.size());
                mCb.setChecked(false);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
