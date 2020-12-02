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
    EditText edit_mark;
    ListView listview_gamelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_layout);
        btnNavMainActivity = (Button) findViewById(R.id.btnNavMainActivity);
        edit_name = (EditText) findViewById(R.id.edit_name);
        add_Game = (Button) findViewById(R.id.add_game);
        listview_gamelist = (ListView) findViewById(R.id.listview_gamelist);
        com.example.spesa.DB_Game_Handler db_game = new com.example.spesa.DB_Game_Handler(this);

        Log.d(TAG, "onCreate: Started.");


        Game Game1 = new Game("TLOU","10");
        Game Game2 = new Game("COD","8");
        ArrayList<Game> game_list = new ArrayList<Game>();
        game_list.add(Game1);
        game_list.add(Game2);

        // ArrayAdapter<String> arrayAdapter_game = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, game_list);
        CustomArrayAdapter_game arrayAdapter_game = new CustomArrayAdapter_game(this, R.layout.listview_gamelist_layout, game_list);
        listview_gamelist.setAdapter(arrayAdapter_game);
        // Food pasta = new Food(0,"Pasta");
        // db_part.addFood(pasta);
        List<com.example.spesa.Game> games = db_game.getAllGame();
        for (com.example.spesa.Game gm : games) {
            game_list.add(gm);
        }
        arrayAdapter_game.notifyDataSetChanged();

        btnNavMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add_Game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inserting Game
                Log.d("Insert: ", "Inserting ..");
                String name_game = edit_name.getText().toString();
                String mark_game = edit_mark.getText().toString();
                db_game.addGame(new com.example.spesa.Game(name_game, mark_game));

                // AGGIORNO LISTA
                List<com.example.spesa.Game> games = db_game.getAllGame();
                game_list.clear();
                for (com.example.spesa.Game gm : games) {
                    game_list.add(gm);
                }
                CustomArrayAdapter_game.notifyDataSetChanged();
                edit_name.setText("");
                edit_mark.setText("");
            }

        });

    }
}
