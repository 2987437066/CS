package com.example.day01;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day01.bean.ResultsBean;
import com.example.xts.greendaodemo.db.ResultsBeanDao;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ResultsBean> list;
    private Context context;

    public MyAdapter(List<ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.desc.setText(list.get(i).getDesc());
        Glide.with(context).load(list.get(i).getUrl()).into(viewHolder.url);
        viewHolder.bt_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultsBeanDao dao = BaseApp.getInstance().getDaoSession().getResultsBeanDao();
                dao.insertOrReplace(list.get(i));
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView url;
        TextView desc;
        Button bt_sc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            url = itemView.findViewById(R.id.url);
            desc = itemView.findViewById(R.id.desc);
            bt_sc = itemView.findViewById(R.id.bt_sc);
        }
    }
    onItemClickListener onItemClickListener;

    public void setOnItemClickListener(MyAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener{
        void onItemClick(int poi);
    }
}
