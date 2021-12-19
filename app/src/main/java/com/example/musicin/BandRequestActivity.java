package com.example.musicin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicin.adapters.BandMembersAdapter;
import com.example.musicin.data.BandRequest;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BandRequestActivity extends AppCompatActivity {
    String  instruments = "";
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band_request);

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        MaterialButton request = findViewById(R.id.requestJoinBand_bttn);
        BandRequest br = getIntent().getParcelableExtra("Request");
        email = getIntent().getStringExtra("email");
        TextView instrumentList = findViewById(R.id.instrumentsInNeed);
        TextView bandName = findViewById(R.id.bandName);
        ImageView bandPhoto = findViewById(R.id.band_photo);
        Picasso.get().load(br.getPhoto()).into(bandPhoto);
        TextView bandGenre = findViewById(R.id.bandGenre);
        bandName.setText(br.getName());
        instruments = br.getInstruments().get(0);
        for(int i = 1; i < br.getInstruments().size(); i++){
            instruments = instruments + ", " + br.getInstruments().get(i);
        }
        instrumentList.setText(instruments);
        bandGenre.setText(br.getGenre());

        RecyclerView membersRv = findViewById(R.id.members_rv);
        BandMembersAdapter adapter = new BandMembersAdapter(br.getMembers());
        membersRv.setAdapter(adapter);
        membersRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DividerItemDecoration divider = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
        membersRv.addItemDecoration(divider);

        adapter.setOnItemClickListener(new BandMembersAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(BandRequestActivity.this,MusicianProfileActivity.class);
                intent.putExtra("request",false);
                intent.putExtra("email",adapter.getMember(position).getEmail());
                startActivity(intent);
            }
        });


        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(br.getInstruments());
            }
        });
    }

    public void openDialog(List<String> instruments){
        ArrayList<String> instrumentsArrayList = new ArrayList<>();
        instrumentsArrayList.addAll(instruments);
        BandRequestDialog dialog = new BandRequestDialog();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("instruments", instrumentsArrayList);
        bundle.putString("email",email);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(),"dialog");
        dialog.getParentFragmentManager().setFragmentResultListener("requestKey", BandRequestActivity.this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String chosenInstrument = result.getString("bundleKey");
                if(!chosenInstrument.equals("")){
                    //TODO: ADD REQUEST TO MUSICIAN IN DATA
                    Toast toast = Toast.makeText(getApplicationContext(), "Request sent!", Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                }
            }
        });
    }
}
