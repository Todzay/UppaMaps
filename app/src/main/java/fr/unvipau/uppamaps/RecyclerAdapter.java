package fr.unvipau.uppamaps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final ArrayList<Model2> list;
    private final Activity activity;

    public RecyclerAdapter(ArrayList<Model2> l, Activity act) {
        list = l;
        activity = act;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        if (list.get(position).getVille()){
            holder.textView.setTextColor(Color.parseColor("#06911D"));
        }else{
            holder.textView.setTextColor(Color.parseColor("#000000"));
        }
        holder.textView.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(view -> {
            if (!list.get(position).getVille()){
                Uri uri =
                        Uri.parse("google.navigation:q="+ list.get(position).getName()
                                +activity.getSharedPreferences("prefs", Context.MODE_PRIVATE)
                                .getString("mode","&mode=w"));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.google.android.apps.maps");
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.item_textview);
        }
    }

}
