package io.ican.hgl.stackoverflow.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.logging.StreamHandler;

import io.ican.hgl.stackoverflow.databinding.QuestionSummaryBinding;

import io.ican.hgl.stackoverflow.entity.question.Summary;


/**
 * Created by swd1 on 16-11-30. 用来显示question-summary的adapter.
 */

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.MyViewHolder> {

    private static final String TAG = "SummaryAdapter";

    private List<Summary> items;
    private Context context;

    public SummaryAdapter(List<Summary> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(QuestionSummaryBinding.inflate(LayoutInflater.from(context), parent, false).getRoot());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.binding.setSummary(items.get(position));
        holder.binding.setLayoutManager(new GridLayoutManager(context, 4));
        holder.binding.setTagAdapter(new TagAdapter<String>(items.get(holder.getAdapterPosition()).tags) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = new TextView(context);
                tv.setText(s);
                return tv;
            }
        });
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

}
