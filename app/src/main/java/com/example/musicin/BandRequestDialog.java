package com.example.musicin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.example.musicin.data.BandRequest;
import com.example.musicin.data.Data;
import com.example.musicin.data.Musician;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class BandRequestDialog extends DialogFragment {
    private TextInputEditText instrumentText;
    private ArrayList<String> instrumentsRequest;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.band_request_dialog,null);

        Data data = Data.getInstance();
        Bundle bundle = getArguments();
        String email = bundle.getString("email");
        instrumentsRequest = bundle.getStringArrayList("instruments");
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

        RadioGroup radio = view.findViewById(R.id.instrument_radio_group);

        for (String instrument: instrumentsPossible) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(instrument);
            radio.addView(radioButton);
        }

        builder.setView(view)
                .setTitle("Choose the instrument you're going to play for this band")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int radioId = radio.getCheckedRadioButtonId();
                        RadioButton radioButton = view.findViewById(radioId);
                        String chosenInstrument = "";
                        if(radioButton != null)
                            chosenInstrument = radioButton.getText().toString();
                        Bundle result = new Bundle();
                        result.putString("bundleKey", chosenInstrument);
                        getParentFragmentManager().setFragmentResult("requestKey", result);
                    }
                });
        //instrument = view.findViewById(R.id.instrument_dialog);
        return builder.create();
    }
}
