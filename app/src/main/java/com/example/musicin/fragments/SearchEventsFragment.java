package com.example.musicin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicin.R;
import com.example.musicin.adapters.SearchEventAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchEventsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_events, container, false);
        RecyclerView list = view.findViewById(R.id.data_list);
        List<String> images = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        titles.add("event1");
        titles.add("event2");
        titles.add("event3");
        titles.add("event4");
        images.add("https://www.collinsdictionary.com/images/full/concert_295115348.jpg");
        images.add("https://www.collinsdictionary.com/images/full/concert_295115348.jpg");
        images.add("https://www.collinsdictionary.com/images/full/concert_295115348.jpg");
        images.add("https://www.collinsdictionary.com/images/full/concert_295115348.jpg");
        SearchEventAdapter searchEventAdapter = new SearchEventAdapter(view.getContext(),images,titles);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(),2,GridLayoutManager.VERTICAL,false);
        list.setLayoutManager(gridLayoutManager);
        list.setAdapter(searchEventAdapter);
        return view;

    }
}
