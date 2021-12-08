package com.example.musicin.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicin.AddFormBandRequestActivity;
import com.example.musicin.BandRequestActivity;
import com.example.musicin.GenreDialog;
import com.example.musicin.InstrumentDialog;
import com.example.musicin.R;
import com.example.musicin.adapters.SearchEventAdapter;
import com.example.musicin.adapters.SearchMemberAdapter;
import com.example.musicin.data.BandRequest;
import com.example.musicin.data.Data;
import com.example.musicin.data.Event;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;

public class SearchMembersFragment extends Fragment {

    private SearchMemberAdapter memberAdapter;
    String instrumentFilter;
    String genreFilter;
    MaterialButton genres;
    MaterialButton instruments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_members, container, false);
        Data data = Data.getInstance();

        genres = view.findViewById(R.id.genre_members);
        genreFilter = null;
        instruments = view.findViewById(R.id.instrument_members);
        instrumentFilter = null;


        String email = getArguments().getString("email");
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
               intent.putExtra("email",email);
               startActivity(intent);
            }
        });

        genres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenreDialog genreDialog = new GenreDialog();
                genreDialog.show(getParentFragmentManager(), "Genre Dialog");
                genreDialog.getParentFragmentManager().setFragmentResultListener("requestKey", getViewLifecycleOwner(), new FragmentResultListener(){
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        genreFilter = result.getString("bundleKey");
                        genres.setText(genreFilter);
                        List<BandRequest> bandRequestListFiltered;
                        bandRequestListFiltered = data.applyFilterMembers(genreFilter,instrumentFilter);
                        memberAdapter.setItems(bandRequestListFiltered);
                    }
                });
            }
        });


        instruments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InstrumentDialog instrumentDialog = new InstrumentDialog();
                instrumentDialog.show(getParentFragmentManager(), "Instrument Dialog");
                instrumentDialog.getParentFragmentManager().setFragmentResultListener("requestKey", getViewLifecycleOwner(), new FragmentResultListener(){
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        instrumentFilter = result.getString("bundleKey");
                        instruments.setText(instrumentFilter);
                        List<BandRequest> bandRequestListFiltered;
                        bandRequestListFiltered = data.applyFilterMembers(genreFilter,instrumentFilter);
                        memberAdapter.setItems(bandRequestListFiltered);
                    }
                });
            }
        });

        return view;
    }
}
