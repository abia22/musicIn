package com.example.musicin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicin.data.BandMember;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView members_rv = view.findViewById(R.id.members_rv);
        ArrayList<BandMember> members = new ArrayList<>();
        members.add(new BandMember("member1", "instrument1", null));
        members.add(new BandMember("member2", "instrument2", null));
        members.add(new BandMember("member3", "instrument3", null));
        BandMembersAdapter adapter = new BandMembersAdapter(members);
        members_rv.setAdapter(adapter);
        members_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
