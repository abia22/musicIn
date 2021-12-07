package com.example.musicin.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicin.R;
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
import java.util.TimeZone;

public class SearchEventsFragment extends Fragment {
    private List<Event>events = new ArrayList<>();
    boolean payment;
    boolean location;
    String genreFilter;
    String dateFilter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_events, container, false);
        Data data = Data.getInstance();
        SwitchMaterial switchPayment = view.findViewById(R.id.payment_event);
        payment = false;
        SwitchMaterial switchLocation = view.findViewById(R.id.location_event);
        location = false;
        MaterialButton date = view.findViewById(R.id.date_event);
        dateFilter = null;
        Spinner genres = view.findViewById(R.id.genre_event);
        genreFilter = null;
        RecyclerView list = view.findViewById(R.id.data_list);
        list.setHasFixedSize(false);
        events = data.getAllEvents();
        SearchEventAdapter searchEventAdapter = new SearchEventAdapter(view.getContext(),events);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(),2,GridLayoutManager.VERTICAL,false);
        list.setLayoutManager(gridLayoutManager);
        list.setAdapter(searchEventAdapter);
        switchPayment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                payment = isChecked;
                List<Event> eventListFiltered;
                eventListFiltered = data.applyFilter(payment,location,genreFilter,dateFilter);
                System.out.println(eventListFiltered.size() + " " + payment);
                searchEventAdapter.setItems(eventListFiltered);
            }
        });
        switchLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                location = isChecked;
                events = data.applyFilter(payment,location,genreFilter,dateFilter);
                searchEventAdapter.setItems(events);
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
                eventListFiltered = data.applyFilter(payment,location,genreFilter,dateFilter);
                searchEventAdapter.setItems(eventListFiltered);

            }
        });


        genres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;

    }
}
