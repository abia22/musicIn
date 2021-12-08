package com.example.musicin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicin.adapters.BandMembersAdapter;
import com.example.musicin.data.BandRequest;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BandRequestActivity extends AppCompatActivity {
    String  instruments = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band_request);
        MaterialButton request = findViewById(R.id.requestJoinBand_bttn);
        BandRequest br = getIntent().getParcelableExtra("Request");
        TextView instrumentList = findViewById(R.id.instrumentsInNeed);
        TextView bandName = findViewById(R.id.bandName);
        ImageView bandPhoto = findViewById(R.id.band_photo);
        Picasso.get().load(br.getPhoto()).into(bandPhoto);
        TextView bandGenre = findViewById(R.id.bandGenre);
        bandName.setText(br.getName());
        br.getInstruments().forEach(i -> instruments = instruments + " " + i);
        instrumentList.setText(instruments);
        bandGenre.setText(br.getGenre());
        RecyclerView membersRv = findViewById(R.id.members_rv);
        BandMembersAdapter adapter = new BandMembersAdapter(br.getMembers());
        membersRv.setAdapter(adapter);
        membersRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DividerItemDecoration divider = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
        membersRv.addItemDecoration(divider);


        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(br.getInstruments());
            }
        });
    }

    public void openDialog(List<String> instruments){
        BandRequestDialog dialog = new BandRequestDialog(instruments);
        dialog.show(getSupportFragmentManager(),"dialog");
    }
}
