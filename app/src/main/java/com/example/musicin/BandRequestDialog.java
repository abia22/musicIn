package com.example.musicin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.musicin.data.BandRequest;
import com.example.musicin.data.Data;
import com.example.musicin.data.Musician;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class BandRequestDialog extends AppCompatDialogFragment {

    private TextInputEditText instrumentText;
    private List<String> instrumentsRequest;

    public BandRequestDialog(List<String> instruments){
        this.instrumentsRequest = instruments;
    }

    public Dialog OnCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.band_request_dialog,null);

        Data data = Data.getInstance();
        Bundle bundle = getArguments();
        String email = bundle.getString("email");
        Musician user = data.getMusician(email);

        String instrumentsUser = user.getInstruments();
        String[] instrumentsUserList = instrumentsUser.split(",");

        List<String> instrumentsPossible = new ArrayList<>();


        for(int i = 0; i < instrumentsUserList.length; i++ ){
            for(int j = 0; j < instrumentsRequest.size(); j++){
                if(instrumentsUserList[i].equalsIgnoreCase(instrumentsRequest.get(j))){
                    instrumentsPossible.add(instrumentsUserList[i]);
                }
            }
        }



        Spinner s = view.findViewById(R.id.instrument_spinner);
        ArrayAdapter<String> instrumentsAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item,instrumentsPossible);
        instrumentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(instrumentsAdapter);

        builder.setView(view)
                .setTitle("Choose Instrument")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        //instrument = view.findViewById(R.id.instrument_dialog);
        return builder.create();
    }
}
