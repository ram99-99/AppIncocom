package com.example.fininfocom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<MyDataModel> dataList;

    public MyAdapter(List<MyDataModel> dataList) {
        this.dataList = dataList;
    }

    public List<MyDataModel> getData() {
        return dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyDataModel dataModel = dataList.get(position);
        holder.nameTextView.setText(dataModel.getName());
        holder.ageTextView.setText(String.valueOf(dataModel.getAge()));
        holder.cityTextView.setText(dataModel.getCity());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView ageTextView;
        public TextView cityTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            ageTextView = itemView.findViewById(R.id.textViewAge);
            cityTextView = itemView.findViewById(R.id.textViewCity);
        }
    }

    public void updateData(List<MyDataModel> newData) {
        dataList.clear();
        dataList.addAll(newData);
        notifyDataSetChanged();
    }
}
