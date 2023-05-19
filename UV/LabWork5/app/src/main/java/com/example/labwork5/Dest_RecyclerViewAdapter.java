package com.example.labwork5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Dest_RecyclerViewAdapter extends RecyclerView.Adapter<Dest_RecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<DestinationModel> destinations;

    public Dest_RecyclerViewAdapter(Context context, ArrayList<DestinationModel> destinations, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.destinations = destinations;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public Dest_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new Dest_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Dest_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvCity.setText(destinations.get(position).getCity());
        holder.tvCountry.setText(destinations.get(position).getCountry());
        System.out.println(destinations.get(position).getImage());
        holder.imageView.setImageResource(destinations.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return destinations.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvCity, tvCountry;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvCity = itemView.findViewById(R.id.textView2);
            tvCountry = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
