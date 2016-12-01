package io.ican.hgl.stackoverflow.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.tcl.hgl.workoverflow.databinding.QuestionSummaryNarrowItemLayoutBinding;
import io.tcl.hgl.workoverflow.entity.Question_Summary;

/**
 * Created by swd1 on 16-11-30.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Question_Summary> items;
    private Context context;

    public MyAdapter(List<Question_Summary> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(QuestionSummaryNarrowItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false).getRoot());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.binding.setQSN(items.get(position));
        holder.binding.setAdapter(new TagAdapter(context, items.get(position).tags));
        holder.binding.setLayoutManager(new GridLayoutManager(context, 4));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        QuestionSummaryNarrowItemLayoutBinding binding;

        MyViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public static void ItemClick(Question_Summary item) {
        Log.e("HHH", item.url);
    }
}
