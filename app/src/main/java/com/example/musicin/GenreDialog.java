package com.example.musicin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.musicin.data.Data;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class GenreDialog extends DialogFragment {

    private MaterialAutoCompleteTextView genreEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.select_genre_dialog, null);

        builder.setView(view)
                .setTitle("Filter by Genre")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String chosenGenre = genreEditText.getText().toString();
                        if(!chosenGenre.equals("")){
                            Bundle result = new Bundle();
                            result.putString("bundleKey", chosenGenre);
                            getParentFragmentManager().setFragmentResult("requestKey", result);
                        }

                    }
                });

        genreEditText = view.findViewById(R.id.genre_ed_txt_dialog);
        ArrayAdapter<String> genresAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, Data.genres);
        genreEditText.setAdapter(genresAdapter);

        return builder.create();
    }

}
