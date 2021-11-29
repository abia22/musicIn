package com.example.musicin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicin.data.BandMember;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.ViewHolder> {
    private List<BandMember> memberList;

    public MembersAdapter(List<BandMember> memberList){
        this.memberList = memberList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.member_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BandMember member = memberList.get(position);

        ImageView photo = holder.photo;
        Picasso.get().load(member.getPhoto()).into(photo);
        TextView name = holder.name;
        name.setText(member.getName());
        TextView instrument = holder.instrument;
        instrument.setText(member.getInstrument());
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView photo;
        public TextView name;
        public TextView instrument;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.memberBand_photo);
            name = itemView.findViewById(R.id.membersName_txt);
            instrument = itemView.findViewById(R.id.instrumentMember_txt);
        }
    }
}
