package dev.brkic.anniething.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;
import java.util.Locale;

import dev.brkic.anniething.R;
import dev.brkic.anniething.fragments.ChampionRotationFragment;
import dev.brkic.anniething.fragments.InfoFragment;
import dev.brkic.anniething.fragments.MasteryFragment;
import dev.brkic.anniething.fragments.StatusFragment;
import dev.brkic.anniething.models.Mastery;
import dev.brkic.anniething.models.Profile;
import dev.brkic.anniething.models.ProfileInfo;

public class ProfileScreenSlidePagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.profile_tab_text_1, R.string.profile_tab_text_2,R.string.profile_tab_text_3};
    private final Context mContext;
    private Profile profileInfo;
    private List<Mastery> masteries;

    public ProfileScreenSlidePagerAdapter(Context context, FragmentManager fm, Profile profileInfo, List<Mastery> masteries) {
        super(fm);
        mContext = context;
        this.profileInfo=profileInfo;
        this.masteries=masteries;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:InfoFragment infoFragment = InfoFragment.newInstance();
                infoFragment.setProfile(profileInfo);
                return infoFragment;
            case 1:
                InfoFragment infoFragment1 = InfoFragment.newInstance();
                infoFragment1.setProfile(profileInfo);
                return infoFragment1;
            case 2:
                MasteryFragment masteryFragment = MasteryFragment.newInstance();
                masteryFragment.setMasteries(masteries);
                return masteryFragment;
            default:InfoFragment infoFragment2 = InfoFragment.newInstance();
                infoFragment2.setProfile(profileInfo);
                return infoFragment2;
        }
    }

    @Nullable
    @Override
    public String getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}