package com.example.spesa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayAdapter_game extends ArrayAdapter<Game> {

    private static final String TAG = "CustomArrayAdapter_part";
    private Context myContext;
    int myResource;

    public CustomArrayAdapter_game(@NonNull Context context, int resource, @NonNull List<Game> objects) {
        super(context, resource, objects);
        myContext = myContext;
        myResource = myResource;
    }

    public CustomArrayAdapter_game(@NonNull Context context, int resource, @NonNull ArrayList<Game> objects) {
        super(context, resource, objects);
        myContext = context;
        myResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(myContext);
            convertView = vi.inflate(myResource, null);
        }

        String name = getItem(position).getName();
        String mark = getItem(position).getMark();

        Game game = new Game(name,mark);

        LayoutInflater inflater = LayoutInflater.from(myContext);
        convertView = inflater.inflate(myResource, parent, false);

        TextView tvname = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvmark = (TextView) convertView.findViewById(R.id.textView2);
        tvname.setText(name);
        tvmark.setText(mark);

        return convertView;

    }
}
