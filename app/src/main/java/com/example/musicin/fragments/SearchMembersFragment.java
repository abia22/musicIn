package com.example.musicin.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.musicin.AddFormBandRequestActivity;
import com.example.musicin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SearchMembersFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_members, container, false);

        FloatingActionButton add_request_bttn = view.findViewById(R.id.add_request_fab);
        add_request_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddFormBandRequestActivity.class));
            }
        });

        return view;
    }
}
