package com.example.spesa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

        Log.d(TAG, "onCreate: Started.");

        btnNavMainActivity.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                finish();
            }
        });
    }
}
