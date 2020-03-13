package com.example.day01;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.day01.bean.ResultsBean;

import java.util.List;

public class MyBAdapter extends RecyclerView.Adapter<MyBAdapter.ViewHolder> {
    private List<ResultsBean> resultsBeans;
    private Context context;

    public MyBAdapter(List<ResultsBean> resultsBeans, Context context) {
        this.resultsBeans = resultsBeans;
        this.context = context;
    }

    private boolean selectAll = true;

    public void setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.items, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context).load(resultsBeans.get(i).getUrl()).into(viewHolder.urls);
        viewHolder.cbs.setTag(i);
        if (selectAll) {
            viewHolder.cbs.setVisibility(View.VISIBLE);
        } else {
            viewHolder.cbs.setVisibility(View.INVISIBLE);
        }
        viewHolder.cbs.setChecked(resultsBeans.get(i).isSelect);
        viewHolder.cbs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int pos = (int) compoundButton.getTag();
                resultsBeans.get(pos).isSelect = b;
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListenenr != null) {
                    onItemClickListenenr.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView urls;
        CheckBox cbs;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            urls = itemView.findViewById(R.id.urls);
            cbs = itemView.findViewById(R.id.cbs);
        }
    }

    onItemClickListenenr onItemClickListenenr;

    public void setOnItemClickListenenr(MyBAdapter.onItemClickListenenr onItemClickListenenr) {
        this.onItemClickListenenr = onItemClickListenenr;
    }

    public interface onItemClickListenenr {
        void onItemClick(int pi);
    }
}
