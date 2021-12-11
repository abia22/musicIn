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

public class HomepageEventsAdapter extends RecyclerView.Adapter<HomepageEventsAdapter.ViewHolder> {
    private List<Event> eventList;
    OnItemClickListener listener;

    public HomepageEventsAdapter(List<Event> eventList){
        this.eventList = eventList;
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.event_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = eventList.get(position);

        ImageView photo = holder.photo;
        Picasso.get().load(event.getPhoto()).into(photo);
        TextView name_tv = holder.name;
        name_tv.setText(event.getName());
        TextView location_tv = holder.location;
        location_tv.setText(event.getLocation());
        TextView date_tv = holder.date;
        date_tv.setText(event.getDate());



    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView photo;
        public TextView name;
        public TextView location;
        public TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.event_photo);
            name = itemView.findViewById(R.id.event_name_txt);
            location = itemView.findViewById(R.id.event_location_txt);
            date = itemView.findViewById(R.id.event_date_txt);

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

    public Event getEvent(int position){
       return  eventList.get(position);
    }
}
