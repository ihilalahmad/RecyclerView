package com.example.recyclerviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Details> detailsList;
    private ItemClickListener mItemListener;

    public MyAdapter(List<Details> detailsList, ItemClickListener itemClickListener) {
        this.detailsList = detailsList;
        this.mItemListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.rv_items_list,
                parent,
                false
        );
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(detailsList.get(position).getTitle());
        holder.tvDesc.setText(detailsList.get(position).getDescription());

        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(detailsList.get(position));//this will get the position of our item in RecyclerView..
        });
    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public interface ItemClickListener{
        void onItemClick(Details details);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDescription);
        }
    }
}
