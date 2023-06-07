package com.example.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Note> {

    public MyAdapter(Context context, List<Note> objects) {
        super(context, R.layout.list_item, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);

        TextView titleTextView = itemView.findViewById(R.id.titleTextView);
        titleTextView.setText(getItem(position).getTitle());

        return itemView;
    }
}
