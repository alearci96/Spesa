package com.example.spesa;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> myFragmentList = new ArrayList<>();
    private final List<String> myFragmentTitleList = new ArrayList<>();

    public SectionsStatePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        myFragmentList.add(fragment);
        myFragmentTitleList.add(title);
    }


    @Override
    public Fragment getItem(int position) {
        return myFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return myFragmentList.size();
    }
}
