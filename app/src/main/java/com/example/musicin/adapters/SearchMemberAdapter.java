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
import com.example.musicin.data.BandRequest;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchMemberAdapter extends RecyclerView.Adapter<SearchMemberAdapter.ViewHolder>{

    List<BandRequest> bandRequests;
    Context context;
    LayoutInflater inflater;

    public SearchMemberAdapter(Context ctx, List<BandRequest> bandRequests){
        this.bandRequests = bandRequests;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void setItems(List<BandRequest> myList) {
        this.bandRequests.clear();
        this.bandRequests.addAll(myList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchMemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grid,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(bandRequests.get(position).getName());
        Picasso.get().load(bandRequests.get(position).getPhoto()).into(holder.gridIcon);
    }

    @Override
    public int getItemCount() {
        return bandRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.search_title);
            gridIcon = itemView.findViewById(R.id.search_image);
        }
    }
}
