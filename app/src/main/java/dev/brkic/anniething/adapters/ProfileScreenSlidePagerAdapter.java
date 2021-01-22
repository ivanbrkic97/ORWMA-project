package dev.brkic.anniething.adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.Locale;

import dev.brkic.anniething.fragments.InfoFragment;

public class ProfileScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 3;
    private static final String BASE_NAME = "Tab #%d";
    public ProfileScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return InfoFragment.newInstance("This is fragment #1");
            case 1:
                return InfoFragment.newInstance("This is fragment #2");
            default:
                return InfoFragment.newInstance("This is fragment #3");
        }
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return String.format(Locale.getDefault(), BASE_NAME, position + 1);
    }
    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}