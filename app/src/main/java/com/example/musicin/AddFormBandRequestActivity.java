package com.example.musicin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddFormBandRequestActivity extends AppCompatActivity {

    private static ArrayList<String> instrumentsList;
    private static ListView instruments_lv;
    private static ArrayList<String> membersList;
    private static ListView members_lv;
    private static InstrumentsListViewAdapter instrumentsAdapter;
    private static MembersListViewAdapter membersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form_band_request);

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
                    instrumentsAdapter.notifyDataSetChanged();
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
                    membersAdapter.notifyDataSetChanged();
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