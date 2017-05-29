package com.developers.oraclehr;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by root on 10/05/17.
 */

public class MyListView extends ArrayAdapter {
    ArrayList<String> tables;
    Activity activity;

    public MyListView(Activity activity, ArrayList tables) {
        super(activity, R.layout.lista, tables);
        this.tables = tables;
        this.activity = activity;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View v = inflater.inflate(R.layout.lista, null, false);
        TextView textView = (TextView)v.findViewById(R.id.tv_tables);

        textView.setText(tables.get(position));
        return v;
    }
}
