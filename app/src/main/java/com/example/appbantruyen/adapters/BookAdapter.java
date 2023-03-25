package com.example.appbantruyen.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbantruyen.databinding.ItemBookBinding;
import com.example.appbantruyen.model.Books;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder>
{
    private List<Books> booksList;


    public BookAdapter(List<Books> booksList) {
        this.booksList = booksList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookBinding binding = ItemBookBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setBinding(booksList.get(position));
        Glide.with(holder.itemView).load(booksList.get(position).getStrBookThumb()).into(holder.binding.imgBook);
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemBookBinding binding;

        public MyViewHolder(ItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        private void setBinding(Books books)
        {
            binding.setBookitem(books);
            binding.executePendingBindings();

        }
    }
}
