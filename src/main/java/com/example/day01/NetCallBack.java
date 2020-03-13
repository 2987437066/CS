package com.example.day01;

import com.example.day01.bean.ResultsBean;

import java.util.List;

public interface NetCallBack {
    void onSuccess(List<ResultsBean> beans);
    void onFail(String str);
}
