package io.ican.hgl.stackoverflow.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.ican.hgl.stackoverflow.databinding.TagsLayoutBinding;

/**
 * Created by swd1 on 16-12-1.
 *
 */

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagViewHolder> {
    private Context context;
    private ArrayList<String> tags;

    public TagAdapter(Context context, ArrayList<String> tags) {
        this.context = context;
        this.tags = tags;
    }

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TagViewHolder(TagsLayoutBinding.inflate(LayoutInflater.from(context), parent, false).getRoot());
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {
        holder.binding.setTagName(tags.get(position));
        holder.binding.tagTextView.setBackgroundColor(Color.alpha(0xFEDCBA>>>position));
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    class TagViewHolder extends RecyclerView.ViewHolder {
        TagsLayoutBinding binding;

        public TagViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
