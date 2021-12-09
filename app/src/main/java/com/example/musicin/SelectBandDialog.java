package com.example.musicin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class SelectBandDialog extends DialogFragment {
    private RadioGroup bands;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.select_band_dialog, null);

        bands = view.findViewById(R.id.select_band_radio);

        Bundle bundle = getArguments();
        ArrayList<String> bandNames = bundle.getStringArrayList("bands");

        for (String name: bandNames) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(name);
            bands.addView(radioButton);
        }


        builder.setView(view)
                .setTitle("Select a band")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int radioId = bands.getCheckedRadioButtonId();
                        String chosenBand = "";
                        RadioButton radioButton = view.findViewById(radioId);
                        if (radioButton != null)
                            chosenBand = radioButton.getText().toString();
                        Bundle result = new Bundle();
                        result.putString("bundleKey", chosenBand);
                        getParentFragmentManager().setFragmentResult("requestKey", result);
                    }
                });

        return builder.create();
    }
}
