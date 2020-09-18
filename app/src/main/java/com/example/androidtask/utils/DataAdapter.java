package com.example.androidtask.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidtask.R;
import com.example.androidtask.model.Record;
import com.example.androidtask.year.details.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.androidtask.ui.home.HomeViewModel.downYears;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    public static List<Boolean> listDropDown = new ArrayList<>();
    String year;

    List<Record> records;
    Context context;

    public DataAdapter(List<Record> records, Context context) {
        this.records = records;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Record item = records.get(position);
        holder.date_record.setText(item.getQuarter());
        holder.record.setText(item.getVolumeOfMobileData());

        // If condition quarter down
        for (int i = 0; i < downYears.size(); i++) {
            if (records.get(position).getQuarter().contains(downYears.get(i))) {
                listDropDown.add(true);
                holder.image_down.setVisibility(View.VISIBLE);
                Glide.with(holder.itemView)
                        .load(context.getDrawable(R.drawable.down))
                        .into(holder.image_down);

            }
            else
                listDropDown.add(false);
        }


        holder.image_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                year = records.get(position).getQuarter().substring(0, 4);
                intent.putExtra("The_Year", year);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return records == null ? 0 : records.size();
    }


    public void changeData(List<Record> items) {
        this.records = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date_record;
        TextView record;
        ImageView image_down;

        public ViewHolder(@NonNull View rootView) {
            super(rootView);
            date_record = rootView.findViewById(R.id.date_record);
            record = rootView.findViewById(R.id.record);
            image_down = rootView.findViewById(R.id.image_down);
        }
    }

}
