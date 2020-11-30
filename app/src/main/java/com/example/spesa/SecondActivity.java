package com.example.spesa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    Button btnNavMainActivity;
    Button add_Game;
    EditText edit_name;
    ListView listview_gamelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_layout);
        Button btnNavMainActivity = (Button) findViewById(R.id.btnNavMainActivity);
        edit_name = (EditText) findViewById(R.id.edit_name);
        add_Game = (Button) findViewById(R.id.add_game);
        com.example.spesa.DB_Part_Handler db_game = new com.example.spesa.DB_Game_Handler(getActivity());

        Log.d(TAG, "onCreate: Started.");

        btnNavMainActivity.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                finish();
            }
        });
        List<String> game_list = new ArrayList<String>();
        ArrayAdapter<String> arrayAdapter_part = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, game_list);
        listview_gamelist.setAdapter(arrayAdapter_part);
        // Food pasta = new Food(0,"Pasta");
        // db_part.addFood(pasta);
        List<com.example.spesa.Game> games = db_game.getAllGame();
        for (com.example.spesa.Game gm : games) {
            game_list.add(gm.getName());
        }
        arrayAdapter_part.notifyDataSetChanged();
    }
}
