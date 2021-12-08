package com.example.musicin;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicin.data.Event;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;


public class RequestEventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_event);

        Event event = getIntent().getParcelableExtra("event");

        TextView name_tv = findViewById(R.id.event_name_request_txt);
        ImageView photo_iv = findViewById(R.id.event_request_photo);
        TextView location_tv = findViewById(R.id.event_location_request_txt);
        TextView info_tv = findViewById(R.id.event_info_request_txt);
        TextView contact_tv = findViewById(R.id.event_contact_request_txt);
        MaterialButton sendRequest_btn = findViewById(R.id.request_join_event_btn);

        name_tv.setText(event.getName());
        Picasso.get().load(event.getPhoto()).into(photo_iv);
        location_tv.setText("Location: " + event.getLocation());
        info_tv.setText(event.getInfo());
        contact_tv.setText("If you want to get more information about us, you can find it here: " + event.getContact());

        sendRequest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
