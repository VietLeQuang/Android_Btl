package com.example.appbantruyen.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbantruyen.databinding.ItemPopularBinding;
import com.example.appbantruyen.listener.EventClickListener;
import com.example.appbantruyen.model.Books;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.MyViewHolder>{
    private List<Books> list;
    private EventClickListener listener;

    public PopularAdapter(List<Books> list, EventClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public PopularAdapter(List<Books> list){
        this.list=list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPopularBinding itemPopularBinding=ItemPopularBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MyViewHolder(itemPopularBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setBinding(list.get(position));
        Glide.with(holder.itemView).load(list.get(position).getStrBookThumb()).into(holder.binding.imgPopular);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ItemPopularBinding binding;

        public MyViewHolder( ItemPopularBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        private void setBinding(Books books){
            binding.setPopular(books);
            binding.executePendingBindings();
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPopularClick(books);
                }
            });
        }
    }
}
