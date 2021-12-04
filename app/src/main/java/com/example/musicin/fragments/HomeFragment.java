package com.example.musicin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicin.adapters.BandMembersAdapter;
import com.example.musicin.R;
import com.example.musicin.data.Band;
import com.example.musicin.data.Musician;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Bundle data = getArguments();
        Musician user = data.getParcelable("user");

        RecyclerView members_rv = view.findViewById(R.id.members_rv);
        Spinner bands_sp = view.findViewById(R.id.my_bands_spinner);
        List<Band> bandList = user.getBands();
        List<String> bandNames = new ArrayList<>(bandList.size());
        if (bandList.isEmpty())
            bandNames.add("No Bands Formed Yet");
        else{
            for (Band band: bandList) {
                bandNames.add(band.getName());
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, bandNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bands_sp.setAdapter(arrayAdapter);

        /*ArrayList<BandMember> members = new ArrayList<>();
        members.add(new BandMember("member1", "instrument1", null));
        members.add(new BandMember("member2", "instrument2", null));
        members.add(new BandMember("member3", "instrument3", null));*/

        bands_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!bandNames.get(i).equals("No Bands Formed Yet")) {
                    BandMembersAdapter adapter = new BandMembersAdapter(bandList.get(i).getMembers());
                    members_rv.setAdapter(adapter);
                    members_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                    DividerItemDecoration divider = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
                    members_rv.addItemDecoration(divider);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapterView.setSelection(0);
            }
        });





        return view;
    }
}
