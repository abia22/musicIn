package com.example.musicin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.musicin.adapters.FormedMembersListViewAdapter;
import com.example.musicin.adapters.InstrumentsListViewAdapter;
import com.example.musicin.adapters.MembersListViewAdapter;
import com.example.musicin.data.Band;
import com.example.musicin.data.BandMember;
import com.example.musicin.data.Data;
import com.example.musicin.fragments.HomeFragment;
import com.example.musicin.utils.NonScrollListView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

public class AddFormedBandActivity extends AppCompatActivity {

    private static ArrayList<String> membersList;
    private static NonScrollListView members_lv;
    private static FormedMembersListViewAdapter membersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_formed_band);

        Data data = Data.getInstance();

        TextInputEditText bandName_ed_txt = findViewById(R.id.band_name_ed_txt);
        AutoCompleteTextView genre_ed_txt = findViewById(R.id.genre_actv);
        AutoCompleteTextView add_members_ed_txt = findViewById(R.id.members_ed_txt);
        members_lv = findViewById(R.id.members_lv);
        ImageView add_member_btn = findViewById(R.id.add_member_img);

        ArrayAdapter<String> genresAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Data.genres);
        genre_ed_txt.setAdapter(genresAdapter);

        ArrayAdapter<String> usersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Data.appUsers);
        add_members_ed_txt.setAdapter(usersAdapter);

        membersList = new ArrayList<>();
        membersAdapter = new FormedMembersListViewAdapter(getApplicationContext(), membersList);
        members_lv.setAdapter(membersAdapter);

        add_member_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = add_members_ed_txt.getText().toString();
                if(text == null || text.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please enter a user", Toast.LENGTH_SHORT).show();
                } else if(membersList.contains(text)) {
                    Toast.makeText(getApplicationContext(), "This user was already added", Toast.LENGTH_SHORT).show();
                } else {
                    add_members_ed_txt.setText("");
                    membersList.add(text);
                    members_lv.setAdapter(membersAdapter);
                }
            }
        });

        MaterialButton add_btn = findViewById(R.id.add_band_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bandName_ed_txt.getText().length() == 0 || genre_ed_txt.getText().length() == 0 || membersList.isEmpty()){
                    Toast.makeText(getApplicationContext(), "All parameters must be filled", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayList<BandMember> bandMembers = new ArrayList<>();
                    for (String name : membersList) {
                        BandMember member = data.getBandMember(name);
                        if (member != null)
                            bandMembers.add(member);
                    }

                    Intent intent = new Intent();
                    intent.putExtra("bandName", bandName_ed_txt.getText().toString());
                    intent.putExtra("members", bandMembers);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

    public static void removeMember(int position) {
        membersList.remove(position);
        members_lv.setAdapter(membersAdapter);
    }
}