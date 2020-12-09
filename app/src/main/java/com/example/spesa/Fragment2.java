package com.example.spesa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    private static final String TAG = "Fragment2";

    private Button btnNavFrag1;
    private Button btnNavFrag2;
    private Button btnNavFrag3;
    private Button btnNavSecondActivity;
    ListView myListView;
    ListView myListView_part;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout, container,false);

        btnNavFrag1 = (Button) view.findViewById(R.id.btnNavFrag1);
        btnNavFrag2 = (Button) view.findViewById(R.id.btnNavFrag2);
        btnNavFrag3 = (Button) view.findViewById(R.id.btnNavFrag3);
        btnNavSecondActivity = (Button) view.findViewById(R.id.btnNavSecondActivity);
        Log.d(TAG, "onCreateView: started.");

        myListView = (ListView) view.findViewById(R.id.listview_person);
        myListView_part = (ListView) view.findViewById(R.id.listview_custom);

        Person Ale1 = new Person("Ale","05-11-1996","M");
        Person Ale2 = new Person("Ale","05-11-1996","M");
        Food_part Food1 = new Food_part("Pasta","1");
        Food_part Food2 = new Food_part("Riso","3");
        ArrayList<Person> peopleList = new ArrayList<>();
        ArrayList<Food_part> foodList = new ArrayList<>();
        peopleList.add(Ale1);
        peopleList.add(Ale2);
        foodList.add(Food1);
        foodList.add(Food2);

        Log.d(TAG, peopleList.toString());
        Log.d(TAG, foodList.toString());
        CustomArrayAdapter adapter = new CustomArrayAdapter(getActivity(),R.layout.adapter_view_layout, peopleList);
        CustomArrayAdapter_part adapter_part = new CustomArrayAdapter_part(getActivity(),R.layout.listview_particular_layout, foodList);
        myListView.setAdapter(adapter);
        myListView_part.setAdapter(adapter_part);


        btnNavFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to fragment 1", Toast.LENGTH_SHORT).show();

                ((MainActivity)getActivity()).setViewPager(0);
            }
        });
        btnNavFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to fragment 2", Toast.LENGTH_SHORT).show();

                ((MainActivity)getActivity()).setViewPager(1);
            }
        });
        btnNavFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to fragment 3", Toast.LENGTH_SHORT).show();

                ((MainActivity)getActivity()).setViewPager(2);
            }
        });
        btnNavSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to Second Activity", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), com.example.spesa.SecondActivity.class);
                startActivity(intent);
            }
        });

        return view;


    }
}



