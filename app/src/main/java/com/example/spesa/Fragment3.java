package com.example.spesa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {
    private static final String TAG = "Fragment3";

    private Button btnNavFrag1;
    private Button btnNavFrag2;
    private Button btnNavFrag3;
    private Button btn_popup;
    private Button btnNavSecondActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3_layout, container,false);

        btnNavFrag1 = (Button) view.findViewById(R.id.btnNavFrag1);
        btnNavFrag2 = (Button) view.findViewById(R.id.btnNavFrag2);
        btnNavFrag3 = (Button) view.findViewById(R.id.btnNavFrag3);
        btn_popup = (Button) view.findViewById(R.id.btn_popup);
        btnNavSecondActivity = (Button) view.findViewById(R.id.btnNavSecondActivity);
        Log.d(TAG, "onCreateView: started.");

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

        btn_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity(),v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item1:
                                Toast.makeText(getActivity(), "Item 1 clicked", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.item2:
                                Toast.makeText(getActivity(), "Item 2 clicked", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.item3:
                                Toast.makeText(getActivity(), "Item 3 clicked", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.item4:
                                Toast.makeText(getActivity(), "Item 4 clicked", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }

                });
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });

        return view;
    }

}



