package com.example.musicin.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicin.AddFormedBandActivity;
import com.example.musicin.EventRequestActivity;
import com.example.musicin.MusicianProfileActivity;
import com.example.musicin.adapters.BandMembersAdapter;
import com.example.musicin.R;
import com.example.musicin.adapters.HomepageEventsAdapter;
import com.example.musicin.adapters.NotificationAdapter;
import com.example.musicin.data.Band;
import com.example.musicin.data.BandMember;
import com.example.musicin.data.Data;
import com.example.musicin.data.Event;
import com.example.musicin.data.Musician;
import com.example.musicin.data.MusicianProfile;
import com.example.musicin.data.Notification;
import com.example.musicin.data.Request;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    List<Notification> notificationsList;
    NotificationAdapter notificationAdapter;
    BandMembersAdapter membersAdapter;
    ArrayAdapter<String> bandsAdapter;
    List<Band> bandList;
    List<String> bandNames;
    Musician user;
    Data data;

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result != null && result.getResultCode() == RESULT_OK){
                if (result.getData() != null){
                    String bandName = result.getData().getStringExtra("bandName");
                    ArrayList<BandMember> members = result.getData().getParcelableArrayListExtra("members");
                    Band band = new Band(bandName, members);
                    user.addBand(band);
                    bandNames.add(band.getName());
                    bandsAdapter.notifyDataSetChanged();
                }
            }
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        data = Data.getInstance();

        EventBus.getDefault().register(this);

        Bundle bundle = getArguments();
        String email = bundle.getString("email");
        user = data.getMusician(email);

        RecyclerView members_rv = view.findViewById(R.id.members_rv);
        Spinner bands_sp = view.findViewById(R.id.my_bands_spinner);
        bandList = new ArrayList<>();
        bandList = user.getBands();
        bandNames = new ArrayList<>(bandList.size());
        if (bandList.isEmpty())
            bandNames.add("No Bands Formed Yet");
        else{
            for (Band band: bandList) {
                bandNames.add(band.getName());
            }
        }

        DividerItemDecoration divider = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);

        bandsAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, bandNames);
        bandsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bands_sp.setAdapter(bandsAdapter);

        bands_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!bandNames.get(i).equals("No Bands Formed Yet")) {
                    membersAdapter = new BandMembersAdapter(bandList.get(i).getMembers());
                    members_rv.setAdapter(membersAdapter);
                    members_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                    members_rv.addItemDecoration(divider);
                    membersAdapter.setOnItemClickListener(new BandMembersAdapter.OnItemClickListener() {
                        @Override
                        public void OnItemClick(int position) {
                            Intent intent = new Intent(getActivity(),MusicianProfileActivity.class);
                            intent.putExtra("request",false);
                            intent.putExtra("email",bandList.get(i).getMembers().get(position).getEmail());
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapterView.setSelection(0);
            }
        });



        TextView add_band_btn = view.findViewById(R.id.add_formed_band_btn);
        add_band_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startForResult.launch(new Intent(getActivity(), AddFormedBandActivity.class));
            }
        });

        RecyclerView events_rv = view.findViewById(R.id.events_rv);
        List<Event> eventList = data.getMusicianEvents(user.getEmail());
        HomepageEventsAdapter homepageEventsAdapter = new HomepageEventsAdapter(eventList);
        events_rv.setAdapter(homepageEventsAdapter);
        events_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        events_rv.addItemDecoration(divider);
        homepageEventsAdapter.setOnItemClickListener(new HomepageEventsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(getActivity(), EventRequestActivity.class);
                intent.putExtra("request",false);
                intent.putExtra("event",homepageEventsAdapter.getEvent(position));
                startActivity(intent);
            }
        });

        RecyclerView notifications_rv = view.findViewById(R.id.notification_rv);
        notificationsList = data.getMusicianRequests(user.getEmail());
        notificationAdapter = new NotificationAdapter(notificationsList);
        notifications_rv.setAdapter(notificationAdapter);
        notifications_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        notifications_rv.addItemDecoration(divider);

        notificationAdapter.setOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
            @Override
            public void onItemCLick(int position) {
                Notification notification = notificationsList.get(position);
                if(notification instanceof Request){
                    Intent intent = new Intent(getActivity(), MusicianProfileActivity.class);
                    intent.putExtra("email", ((Request) notification).getEmail());
                    intent.putExtra("position", position);
                    intent.putExtra("band", ((Request) notification).getBandToJoin());
                    intent.putExtra("request",true);
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    public static class MessageEvent {
        public final boolean response;
        public final int position;
        public final String bandToJoin;
        public final String emailNewMember;

        public MessageEvent(boolean response, int position, String bandToJoin, String emailNewMember) {
            this.response = response;
            this.position = position;
            this.bandToJoin = bandToJoin;
            this.emailNewMember = emailNewMember;
        }
    }

    @Subscribe
    public void onEvent(MessageEvent event) {
        notificationsList.remove(event.position);
        notificationAdapter.notifyDataSetChanged();
        if (event.response){                //if it's true then the user accepted the request
            ArrayList<Band> userBands = user.getBands();
            Musician musicianRequester = data.getMusician(event.emailNewMember);
            MusicianProfile musicianProfileRequester = data.getMusicianProfile(event.emailNewMember);
            BandMember newMember = new BandMember(musicianRequester.getName(), musicianRequester.getInstruments(), musicianProfileRequester.getPhoto(), musicianRequester.getEmail());
            for (int i = 0; i < userBands.size(); i++) {
                Band band = userBands.get(i);
                if(band.getName().equals(event.bandToJoin)){
                    userBands.remove(band);
                    band.addMember(newMember);
                    userBands.add(band);
                    user.setBands(userBands);
                    membersAdapter.notifyDataSetChanged();
                }
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
