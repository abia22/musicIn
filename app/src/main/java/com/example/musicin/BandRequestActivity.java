package com.example.musicin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.musicin.data.BandRequest;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class BandRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band_request);
        MaterialButton request = findViewById(R.id.requestJoinBand_bttn);

        BandRequest bandRequest1 = new BandRequest(null,null,null);
        List<String> instruments = bandRequest1.getInstruments();

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(instruments);
            }
        });
    }

    public void openDialog(List<String> instruments){
        BandRequestDialog dialog = new BandRequestDialog(instruments);
        dialog.show(getSupportFragmentManager(),"dialog");
    }
}
