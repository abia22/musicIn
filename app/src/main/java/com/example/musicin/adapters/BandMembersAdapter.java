package com.example.musicin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicin.R;
import com.example.musicin.data.BandMember;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BandMembersAdapter extends RecyclerView.Adapter<BandMembersAdapter.ViewHolder> {
    private List<BandMember> memberList;

    public BandMembersAdapter(List<BandMember> memberList){
        this.memberList = memberList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.band_member_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BandMember member = memberList.get(position);

        CircleImageView photo = holder.photo;
        Picasso.get().load(member.getPhoto()).noFade().into(photo);
        TextView name_tv = holder.name;
        name_tv.setText(member.getName());
        TextView instrument_tv = holder.instrument;
        instrument_tv.setText(member.getInstrument());


    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public CircleImageView photo;
        public TextView name;
        public TextView instrument;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.member_photo);
            name = itemView.findViewById(R.id.member_name_txt);
            instrument = itemView.findViewById(R.id.instrument_txt);
        }
    }
}
