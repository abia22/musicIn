package com.example.musicin.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicin.GenreDialog;
import com.example.musicin.R;
import com.example.musicin.EventRequestActivity;
import com.example.musicin.adapters.SearchEventAdapter;
import com.example.musicin.data.Data;
import com.example.musicin.data.Event;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SearchEventsFragment extends Fragment {
    private List<Event>events = new ArrayList<>();
    boolean paid;
    boolean notPaid;
    boolean location;
    String genreFilter;
    String dateFilter;
    MaterialButton genres;
    SearchEventAdapter searchEventAdapter;
    SwitchMaterial switchLocation;
    Data data;

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    switchLocation.setEnabled(true);
                    location = true;
                    events = data.applyFilter(paid,location,genreFilter,dateFilter, notPaid);
                    searchEventAdapter.setItems(events);
                } else {
                    Toast toast = Toast.makeText(getContext(), "Permission is necessary in order to get your location to use for this filter", Toast.LENGTH_LONG);
                    toast.show();
                }
            });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_events, container, false);

        data = Data.getInstance();

        Bundle bundle = getArguments();
        String email = bundle.getString("email");

        SwitchMaterial switchPayment = view.findViewById(R.id.payment_event);
        paid = false;
        SwitchMaterial switchNotPaid = view.findViewById(R.id.not_paid_event);
        notPaid = false;
        switchLocation = view.findViewById(R.id.location_event);
        location = false;
        MaterialButton date = view.findViewById(R.id.date_event);
        dateFilter = null;
        genres = view.findViewById(R.id.genre_event);
        genreFilter = null;

        RecyclerView events_rv = view.findViewById(R.id.data_list);
        events_rv.setHasFixedSize(false);
        events = data.getAllEvents();
        searchEventAdapter = new SearchEventAdapter(view.getContext(),events);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(),2,GridLayoutManager.VERTICAL,false);
        events_rv.setLayoutManager(gridLayoutManager);
        events_rv.setAdapter(searchEventAdapter);
        searchEventAdapter.setOnItemClickListener(new SearchEventAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(getActivity(), EventRequestActivity.class);
                Event event = searchEventAdapter.getEvent(position);
                intent.putExtra("event", event);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        switchPayment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchNotPaid.isEnabled())
                    switchNotPaid.setEnabled(false);
                else
                    switchNotPaid.setEnabled(true);
                notPaid = false;
                paid = isChecked;
                List<Event> eventListFiltered;
                eventListFiltered = data.applyFilter(paid,location,genreFilter,dateFilter, notPaid);
                searchEventAdapter.setItems(eventListFiltered);

            }
        });

        switchNotPaid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (switchPayment.isEnabled())
                    switchPayment.setEnabled(false);
                else
                    switchPayment.setEnabled(true);
                paid = false;
                notPaid = isChecked;
                List<Event> eventListFiltered;
                eventListFiltered = data.applyFilter(paid,location,genreFilter,dateFilter, notPaid);
                searchEventAdapter.setItems(eventListFiltered);
            }
        });

        switchLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ContextCompat.checkSelfPermission(
                        getContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
                    location = isChecked;
                    events = data.applyFilter(paid,location,genreFilter,dateFilter, notPaid);
                    searchEventAdapter.setItems(events);
                } else {
                    requestPermissionLauncher.launch(
                            Manifest.permission.ACCESS_FINE_LOCATION);
                }
            }
        });

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        CalendarConstraints.Builder constraintBuilder = new CalendarConstraints.Builder();
        constraintBuilder.setStart(today)
                         .setValidator(DateValidatorPointForward.now());
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select a date");
        builder.setSelection(today);
        builder.setCalendarConstraints(constraintBuilder.build());
        final MaterialDatePicker materialDatePicker  = builder.build();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getActivity().getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                dateFilter = materialDatePicker.getHeaderText();
                date.setText(dateFilter);
                List<Event> eventListFiltered;
                eventListFiltered = data.applyFilter(paid,location,genreFilter,dateFilter, notPaid);
                searchEventAdapter.setItems(eventListFiltered);

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
                        List<Event> eventListFiltered;
                        eventListFiltered = data.applyFilter(paid,location,genreFilter,dateFilter, notPaid);
                        searchEventAdapter.setItems(eventListFiltered);
                    }
                });
            }
        });

        return view;

    }

}
