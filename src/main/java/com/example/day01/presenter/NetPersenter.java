package com.example.day01.presenter;

import com.example.day01.NetCallBack;
import com.example.day01.bean.ResultsBean;
import com.example.day01.model.NetModel;
import com.example.day01.view.NetView;

import java.util.List;

public class NetPersenter implements NetCallBack {
    private NetModel netModel;
    private NetView netView;

    public NetPersenter(NetView netView) {
        this.netView = netView;
        this.netModel = new NetModel();
    }

    @Override
    public void onSuccess(List<ResultsBean> beans) {
        netView.setData(beans);
    }

    @Override
    public void onFail(String str) {
        netView.showToast(str);
    }

    public void getData() {
        netModel.getData(this);
    }
}
