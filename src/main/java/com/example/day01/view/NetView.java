package com.example.day01.view;

import com.example.day01.bean.ResultsBean;

import java.util.List;

public interface NetView {
    void setData(List<ResultsBean> resultsBeans);
    void showToast(String str);
}
