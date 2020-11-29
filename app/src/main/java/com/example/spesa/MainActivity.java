package com.example.spesa;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private com.example.spesa.SectionsStatePagerAdapter mySectionsStatePagerAdapter;
    private ViewPager myViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started");

        mySectionsStatePagerAdapter = new com.example.spesa.SectionsStatePagerAdapter(getSupportFragmentManager());

        myViewPager = (ViewPager) findViewById(R.id.container);

        //setup the pager
        setupViewPager(myViewPager);


    }

    public void setViewPager(int fragmentNumber){
        myViewPager.setCurrentItem(fragmentNumber);
    }

    private void setupViewPager(ViewPager viewPager) {
        com.example.spesa.SectionsStatePagerAdapter adapter = new com.example.spesa.SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new com.example.spesa.Fragment1(), "Fragment1");
        adapter.addFragment(new com.example.spesa.Fragment2(), "Fragment2");
        adapter.addFragment(new com.example.spesa.Fragment3(), "Fragment3");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        RelativeLayout main_view = (RelativeLayout) findViewById(R.id.main_view);

        switch (item.getItemId()) {
            case R.id.menu_red:
                main_view.setBackgroundColor(Color.RED);
                return true;
            case R.id.menu_blue:
                main_view.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.menu_green:
                main_view.setBackgroundColor(Color.GREEN);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}



