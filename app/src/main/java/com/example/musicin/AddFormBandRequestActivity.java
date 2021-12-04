package com.example.musicin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.musicin.adapters.InstrumentsListViewAdapter;
import com.example.musicin.adapters.MembersListViewAdapter;
import com.example.musicin.data.Data;
import com.example.musicin.utils.NonScrollListView;

import java.util.ArrayList;

public class AddFormBandRequestActivity extends AppCompatActivity {

    private static ArrayList<String> instrumentsList;
    private static NonScrollListView instruments_lv;
    private static ArrayList<String> membersList;
    private static NonScrollListView  members_lv;
    private static InstrumentsListViewAdapter instrumentsAdapter;
    private static MembersListViewAdapter membersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form_band_request);

        AutoCompleteTextView genres_edtxt = findViewById(R.id.genre_actv);
        ArrayAdapter<String> genresAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Data.genres);
        genres_edtxt.setAdapter(genresAdapter);

        EditText instruments_edt = findViewById(R.id.needed_instruments_ed_txt);
        instruments_lv = findViewById(R.id.instruments_lv);
        ImageView addItem_bttn = findViewById(R.id.add_item);
        instrumentsList = new ArrayList<>();
        instrumentsAdapter = new InstrumentsListViewAdapter(getApplicationContext(), instrumentsList);
        instruments_lv.setAdapter(instrumentsAdapter);

        addItem_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = instruments_edt.getText().toString();
                if(text == null || text.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please enter an instrument", Toast.LENGTH_SHORT).show();
                } else if(instrumentsList.contains(text)) {
                    Toast.makeText(getApplicationContext(), "This instrument was already added", Toast.LENGTH_SHORT).show();
                } else {
                    instruments_edt.setText("");
                    instrumentsList.add(text);
                    instruments_lv.setAdapter(instrumentsAdapter);
                }
            }
        });

        EditText members_edt = findViewById(R.id.members_ed_txt);
        members_lv = findViewById(R.id.members_lv);
        ImageView addmember_bttn = findViewById(R.id.add_member_img);
        membersList = new ArrayList<>();
        membersAdapter = new MembersListViewAdapter(getApplicationContext(), membersList);
        members_lv.setAdapter(membersAdapter);

        addmember_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = members_edt.getText().toString();
                if(text == null || text.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please enter a user", Toast.LENGTH_SHORT).show();
                } else if(membersList.contains(text)) {
                    Toast.makeText(getApplicationContext(), "This user was already added", Toast.LENGTH_SHORT).show();
                } else {
                    members_edt.setText("");
                    membersList.add(text);
                    members_lv.setAdapter(membersAdapter);
                }
            }
        });
    }

    public static void removeInstrument(int position) {
        instrumentsList.remove(position);
        instruments_lv.setAdapter(instrumentsAdapter);
    }

    public static void removeMember(int position) {
        membersList.remove(position);
        members_lv.setAdapter(membersAdapter);
    }
}