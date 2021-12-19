package com.example.musicin;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.musicin.adapters.InstrumentsListViewAdapter;
import com.example.musicin.adapters.MembersListViewAdapter;
import com.example.musicin.data.BandMember;
import com.example.musicin.data.BandRequest;
import com.example.musicin.data.Data;
import com.example.musicin.utils.NonScrollListView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddFormBandRequestActivity extends AppCompatActivity {

    private static ArrayList<String> instrumentsList;
    private static NonScrollListView instruments_lv;
    private static ArrayList<String> membersList;
    private static NonScrollListView  members_lv;
    private static InstrumentsListViewAdapter instrumentsAdapter;
    private static MembersListViewAdapter membersAdapter;
    CircleImageView circleImageView;
    Uri photoUri;

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    if(uri != null){
                        circleImageView.setImageURI(uri);
                        photoUri = uri;
                    }

                }
            });

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    mGetContent.launch("image/*");
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Permission is necessary in order to change the profile picture", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form_band_request);

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Data data = Data.getInstance();

        TextInputEditText name_ed_txt = findViewById(R.id.band_input_txt);

        AutoCompleteTextView genres_edtxt = findViewById(R.id.genre_actv);
        ArrayAdapter<String> genresAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Data.genres);
        genres_edtxt.setAdapter(genresAdapter);

        AutoCompleteTextView instruments_edt = findViewById(R.id.needed_instruments_ed_txt);
        ArrayAdapter<String> instrumentsAutoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Data.instruments);
        instruments_edt.setAdapter(instrumentsAutoAdapter);
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

        AutoCompleteTextView members_edt = findViewById(R.id.members_ed_txt);
        ArrayAdapter<String> usersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Data.appUsers);
        members_edt.setAdapter(usersAdapter);
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
        circleImageView = findViewById(R.id.edit_photo);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    mGetContent.launch("image/*");
                } else {
                    requestPermissionLauncher.launch(
                            Manifest.permission.READ_EXTERNAL_STORAGE);
                }
            }
        });

        MaterialButton submit_btn = findViewById(R.id.submit_new_band_request);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bandName = name_ed_txt.getText().toString();
                String genre = genres_edtxt.getText().toString();
                if(bandName.equals("") || genre.equals("") || instrumentsList.isEmpty() || photoUri == null){
                    Toast.makeText(getApplicationContext(), "Only the members list is an optional parameter", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayList<BandMember> bandMembers = new ArrayList<>();
                    for (String name : membersList) {
                        BandMember member = data.getBandMember(name);
                        if (member != null)
                            bandMembers.add(member);
                    }
                    Intent intent = new Intent();
                    List<String> newInstrumentsList = new ArrayList<>();
                    newInstrumentsList.addAll(newInstrumentsList);
                    BandRequest bandRequest = new BandRequest(null, genre, null, bandName, photoUri.toString());
                    intent.putExtra("bandRequest", bandRequest);
                    intent.putExtra("instruments", instrumentsList);
                    intent.putExtra("members", bandMembers);
                    setResult(RESULT_OK, intent);
                    finish();

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