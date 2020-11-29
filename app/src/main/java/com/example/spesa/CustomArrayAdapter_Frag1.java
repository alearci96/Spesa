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

public class CustomArrayAdapter_Frag1 extends ArrayAdapter<Person> {

    private static final String TAG = "CustomArrayAdapter";
    private Context myContext;
    int myResource;

    public CustomArrayAdapter_Frag1(@NonNull Context context, int resource, @NonNull ArrayList<Person> objects) {
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
        String birthday = getItem(position).getBirthday();
        String sex = getItem(position).getSex();

        Person person = new Person(name,birthday,sex);

        LayoutInflater inflater = LayoutInflater.from(myContext);
        convertView = inflater.inflate(myResource, parent, false);

        TextView tvname = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvbirthday = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvsex = (TextView) convertView.findViewById(R.id.textView3);
        tvname.setText(name);
        tvbirthday.setText(birthday);
        tvsex.setText(sex);

        return convertView;

    }
}
