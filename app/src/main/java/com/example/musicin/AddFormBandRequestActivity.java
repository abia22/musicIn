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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form_band_request);

        EditText instruments_edt = findViewById(R.id.needed_instruments_ed_txt);
        ListView instruments_lv = findViewById(R.id.instruments_lv);
        ImageView addItem_bttn = findViewById(R.id.add_item);
        ArrayList<String> instrumentsList = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, instrumentsList);
        instruments_lv.setAdapter(arrayAdapter);

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
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}