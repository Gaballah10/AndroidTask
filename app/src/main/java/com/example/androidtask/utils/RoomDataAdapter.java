package com.example.androidtask.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidtask.Database.Model.Data;
import com.example.androidtask.R;
import com.example.androidtask.year.details.DetailsActivity;

import java.util.List;


public class RoomDataAdapter extends RecyclerView.Adapter<RoomDataAdapter.ViewHolder> {

    String year ;

    List<Data> data;
    Context context;

    public RoomDataAdapter(List<Data> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RoomDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_data, parent, false);
        return new RoomDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomDataAdapter.ViewHolder holder, final int position) {

        Data item = data.get(position);
        holder.dateData.setText(item.getQuarter());
        holder.data.setText(item.getVolumeOfConsumption());

        if (!item.getHadDownquarter().equals(true) || item.getHadDownquarter().equals(null)){
            item.setHadDownquarter(false);
        }
        if (item.getHadDownquarter().equals(true)){
            holder.image_down.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView)
                    .load(context.getDrawable(R.drawable.down))
                    .into(holder.image_down);
        }else
            return;

        holder.image_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                year = data.get(position).getQuarter().substring(0, 4);
                intent.putExtra("The_Year", year);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public void changeData(List<Data> items) {
        this.data = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateData;
        TextView data;
        ImageView image_down;

        public ViewHolder(@NonNull View rootView) {
            super(rootView);
            dateData = rootView.findViewById(R.id.date_record);
            data = rootView.findViewById(R.id.record);
            image_down = rootView.findViewById(R.id.image_down);
        }
    }

}
