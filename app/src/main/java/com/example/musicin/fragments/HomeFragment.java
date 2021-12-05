package com.example.musicin.fragments;

import android.content.Intent;
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

import com.example.musicin.MusicianProfileActivity;
import com.example.musicin.adapters.BandMembersAdapter;
import com.example.musicin.R;
import com.example.musicin.adapters.EventsAdapter;
import com.example.musicin.adapters.NotificationAdapter;
import com.example.musicin.data.Band;
import com.example.musicin.data.Data;
import com.example.musicin.data.Event;
import com.example.musicin.data.Musician;
import com.example.musicin.data.Notification;
import com.example.musicin.data.Request;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Data data = new Data();

        Bundle bundle = getArguments();
        String email = bundle.getString("email");
        Musician user = data.getMusician(email);

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

        DividerItemDecoration divider = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);

        ArrayAdapter<String> bandsAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, bandNames);
        bandsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bands_sp.setAdapter(bandsAdapter);

        bands_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!bandNames.get(i).equals("No Bands Formed Yet")) {
                    BandMembersAdapter adapter = new BandMembersAdapter(bandList.get(i).getMembers());
                    members_rv.setAdapter(adapter);
                    members_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                    members_rv.addItemDecoration(divider);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapterView.setSelection(0);
            }
        });

        RecyclerView events_rv = view.findViewById(R.id.events_rv);
        List<Event> eventList = data.getMusicianEvents(user.getEmail());
        EventsAdapter eventsAdapter = new EventsAdapter(eventList);
        events_rv.setAdapter(eventsAdapter);
        events_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        events_rv.addItemDecoration(divider);

        RecyclerView notifications_rv = view.findViewById(R.id.notification_rv);
        List<Notification> notificationsList = data.getMusicianRequests(user.getEmail());
        NotificationAdapter requestsAdapter = new NotificationAdapter(notificationsList);
        notifications_rv.setAdapter(requestsAdapter);
        notifications_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        notifications_rv.addItemDecoration(divider);

        requestsAdapter.setOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
            @Override
            public void onItemCLick(int position) {
                Notification notification = notificationsList.get(position);
                if(notification instanceof Request){
                    Intent intent = new Intent(getActivity(), MusicianProfileActivity.class);
                    intent.putExtra("email", ((Request) notification).getEmail());
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}
