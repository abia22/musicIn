package com.example.musicin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicin.R;
import com.example.musicin.data.Event;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchEventAdapter extends RecyclerView.Adapter<SearchEventAdapter.ViewHolder> {
    private List<Event> events;
    LayoutInflater inflater;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public SearchEventAdapter(Context ctx, List<Event> events){
        this.events = events;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void setItems(List<Event> myList) {
        this.events.clear();
        this.events.addAll(myList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grid,parent,false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(events.get(position).getName());
        Picasso.get().load(events.get(position).getPhoto()).into(holder.gridIcon);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.search_title);
            gridIcon = itemView.findViewById(R.id.search_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
