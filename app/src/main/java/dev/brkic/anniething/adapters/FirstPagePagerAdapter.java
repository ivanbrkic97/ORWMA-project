package dev.brkic.anniething.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import dev.brkic.anniething.R;
import dev.brkic.anniething.fragments.ChampionRotationFragment;
import dev.brkic.anniething.fragments.InfoFragment;
import dev.brkic.anniething.fragments.StatusFragment;
import dev.brkic.anniething.models.Champion;

public class FirstPagePagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public FirstPagePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ChampionRotationFragment.newInstance(getPageTitle(position));
            case 1:
                return StatusFragment.newInstance(getPageTitle(position));
            default:
                return ChampionRotationFragment.newInstance(getPageTitle(position));
        }
    }

    @Nullable
    @Override
    public String getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}