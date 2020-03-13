package com.example.day01.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.day01.BaseApp;
import com.example.day01.Main2Activity;
import com.example.day01.MyAdapter;
import com.example.day01.R;
import com.example.day01.bean.ResultsBean;
import com.example.day01.presenter.NetPersenter;
import com.example.day01.view.NetView;

import java.util.ArrayList;
import java.util.List;

public class AFragment extends Fragment implements NetView {
    private RecyclerView mRlv;
    private List<ResultsBean> list;
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.item_rec, null);
        NetPersenter persenter = new NetPersenter(this);
        persenter.getData();
        initView(root);
        initRec();
        return root;
    }

    private void initRec() {
        mRlv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRlv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new MyAdapter(list, getContext());
        mRlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int poi) {
                Intent intent = new Intent(getContext(), Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("poi",poi+"");
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
    }

    private void initView(View root) {
        mRlv = root.findViewById(R.id.rlv);
    }

    @Override
    public void setData(List<ResultsBean> resultsBeans) {
        list.addAll(resultsBeans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(getContext(),str, Toast.LENGTH_SHORT).show();
    }
}
