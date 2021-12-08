package com.example.musicin.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicin.AddFormBandRequestActivity;
import com.example.musicin.BandRequestActivity;
import com.example.musicin.R;
import com.example.musicin.adapters.SearchEventAdapter;
import com.example.musicin.adapters.SearchMemberAdapter;
import com.example.musicin.data.BandRequest;
import com.example.musicin.data.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class SearchMembersFragment extends Fragment {

    private SearchMemberAdapter memberAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_members, container, false);
        Data data = Data.getInstance();

        RecyclerView members_rv = view.findViewById(R.id.data_list_members);
        members_rv.setHasFixedSize(false);
        List<BandRequest> requests = data.getAllRequests();
        memberAdapter = new SearchMemberAdapter(view.getContext(),requests);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(),2,GridLayoutManager.VERTICAL,false);
        members_rv.setLayoutManager(gridLayoutManager);
        members_rv.setAdapter(memberAdapter);

        FloatingActionButton add_request_bttn = view.findViewById(R.id.add_request_fab);
        add_request_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddFormBandRequestActivity.class));
            }
        });

        memberAdapter.setOnItemClickListener(new SearchMemberAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
               BandRequest request  = memberAdapter.getRequest(position);
               Intent intent = new Intent(getActivity(), BandRequestActivity.class);
               intent.putExtra("Request",request);
               startActivity(intent);
            }
        });

        return view;
    }
}
