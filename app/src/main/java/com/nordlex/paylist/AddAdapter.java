package com.nordlex.paylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nordlex.paylist.data.Explanation;
import com.nordlex.paylist.data.Item;

import java.util.ArrayList;

public class AddAdapter extends RecyclerView.Adapter<AddAdapter.AddViewHolder> {
    private final Context context;
    private final ArrayList<Item> items;

    public AddAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public AddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.add_row,parent,false);
        return new AddViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddAdapter.AddViewHolder holder, int position) {
        holder.viewNumCode.setText(String.valueOf(items.get(position).getNumCode()));
        holder.viewCash.setText(items.get(position).toString());
        holder.viewDescription.setText(Explanation.getExplanation(items.get(position).getNumCode()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class AddViewHolder extends RecyclerView.ViewHolder {
        TextView viewNumCode, viewCash, viewDescription;

        public AddViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNumCode = itemView.findViewById(R.id.viewNumCode);
            viewCash = itemView.findViewById(R.id.viewCash);
            viewDescription = itemView.findViewById(R.id.viewDescription);
        }
    }
}
