package com.example.musicin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.musicin.R;
import com.example.musicin.data.Data;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

public class InstrumentDialog extends DialogFragment {

    private MaterialAutoCompleteTextView instrumentEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.select_instrument_dialog, null);

        builder.setView(view)
                .setTitle("Filter by Instrument")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String chosenInstrument = instrumentEditText.getText().toString();
                        if(!chosenInstrument.equals("")){
                            Bundle result = new Bundle();
                            result.putString("bundleKey", chosenInstrument);
                            getParentFragmentManager().setFragmentResult("requestKey", result);
                        }

                    }
                });

        instrumentEditText = view.findViewById(R.id.instrument_ed_txt_dialog);
        ArrayAdapter<String> instrumentsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, Data.instruments);
        instrumentEditText.setAdapter(instrumentsAdapter);

        return builder.create();
    }
}
