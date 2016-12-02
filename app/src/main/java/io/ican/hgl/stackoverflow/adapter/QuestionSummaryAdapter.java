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

import io.ican.hgl.stackoverflow.databinding.QuestionSummaryBinding;

import io.ican.hgl.stackoverflow.entity.question.Question;


/**
 * Created by swd1 on 16-11-30.
 */

public class QuestionSummaryAdapter extends RecyclerView.Adapter<QuestionSummaryAdapter.MyViewHolder> {

    private List<Question> items;
    private Context context;

    public QuestionSummaryAdapter(List<Question> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(QuestionSummaryBinding.inflate(LayoutInflater.from(context), parent, false).getRoot());
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
        QuestionSummaryBinding binding;

        MyViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public static void ItemClick(Question item) {
        Log.e("HHH", item.url);
    }
}
