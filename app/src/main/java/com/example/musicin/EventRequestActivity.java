package com.example.musicin;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import com.example.musicin.data.Band;
import com.example.musicin.data.Data;
import com.example.musicin.data.Event;
import com.example.musicin.data.Musician;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class EventRequestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_event);

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Data data = Data.getInstance();

        Event event = getIntent().getParcelableExtra("event");
        String email = getIntent().getStringExtra("email");
        boolean isRequest = getIntent().getBooleanExtra("request",true);
        Musician musician = data.getMusician(email);

        TextView name_tv = findViewById(R.id.event_name_request_txt);
        ImageView photo_iv = findViewById(R.id.event_request_photo);
        TextView location_tv = findViewById(R.id.event_location_request_txt);
        TextView info_tv = findViewById(R.id.event_info_request_txt);
        TextView contact_tv = findViewById(R.id.event_contact_request_txt);
        MaterialButton sendRequest_btn = findViewById(R.id.request_join_event_btn);
        if(!isRequest){
            sendRequest_btn.setVisibility(View.GONE);
        }


        name_tv.setText(event.getName());
        Picasso.get().load(event.getPhoto()).into(photo_iv);
        location_tv.setText("Location: " + event.getLocation());
        info_tv.setText(event.getInfo());
        contact_tv.setText("If you want to get more information about us, you can find it here: " + event.getContact());

        sendRequest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Band> bandList = musician.getBands();
                if(bandList.isEmpty()){
                    CharSequence text = "You can only request to join an event if you have formed a band!";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                    toast.show();
                }
                ArrayList<String> bandNames = new ArrayList<>(bandList.size());
                for (Band band: bandList) {
                    bandNames.add(band.getName());
                }
                SelectBandDialog selectBandDialog = new SelectBandDialog();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("bands", bandNames);
                selectBandDialog.setArguments(bundle);
                selectBandDialog.show(getSupportFragmentManager(), "Select a band Dialog");
                selectBandDialog.getParentFragmentManager().setFragmentResultListener("requestKey", EventRequestActivity.this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        String chosenBand = result.getString("bundleKey");
                        if(!chosenBand.equals("")){
                            //TODO: ADD REQUEST TO MUSICIAN IN DATA
                            Toast toast = Toast.makeText(getApplicationContext(), "Request sent!", Toast.LENGTH_SHORT);
                            toast.show();
                            finish();
                        }
                    }
                });
            }
        });
    }
}
