package com.nordlex.paylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private final Context context;
    private final ArrayList totalDate;
    private final ArrayList totalClear;
    private final ArrayList totalIncome;
    private final ArrayList totalOutcome;

    public MainAdapter(Context context, ArrayList totalDate,
                       ArrayList totalClear,ArrayList totalIncome,ArrayList totalOutcome) {
        this.context = context;
        this.totalDate = totalDate;
        this.totalClear = totalClear;
        this.totalIncome = totalIncome;
        this.totalOutcome = totalOutcome;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.month_row, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.totalViewDate.setText(String.valueOf(totalDate.get(position)));
        holder.totalViewClear.setText(String.valueOf(totalClear.get(position)));
        holder.totalViewIncome.setText(String.valueOf(totalIncome.get(position)));
        holder.totalViewOutcome.setText(String.valueOf(totalOutcome.get(position)));
    }

    @Override
    public int getItemCount() {
        return totalDate.size();
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {
        TextView totalViewDate, totalViewClear, totalViewIncome, totalViewOutcome;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            totalViewDate = itemView.findViewById(R.id.text_view_date);
            totalViewClear = itemView.findViewById(R.id.text_view_clear);
            totalViewIncome = itemView.findViewById(R.id.text_view_income);
            totalViewOutcome = itemView.findViewById(R.id.text_view_outcome);
        }
    }
}
