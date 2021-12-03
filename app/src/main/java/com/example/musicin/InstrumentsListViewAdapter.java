package com.example.musicin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class InstrumentsListViewAdapter extends ArrayAdapter<String> {

    ArrayList<String> list;
    Context context;

    public InstrumentsListViewAdapter(Context context, ArrayList<String> list) {
        super(context, R.layout.num_del_row, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.num_del_row, null);
            TextView name = convertView.findViewById(R.id.text);
            ImageView remove = convertView.findViewById(R.id.remove);
            TextView number = convertView.findViewById(R.id.number);

            number.setText(position + 1 + ".");
            name.setText(list.get(position));

            // Listeners for duplicating and removing an item.
            // They use the static removeItem and addItem methods created in MainActivity.
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddFormBandRequestActivity.removeInstrument(position);
                }
            });
        }
        return convertView;
    }
}
