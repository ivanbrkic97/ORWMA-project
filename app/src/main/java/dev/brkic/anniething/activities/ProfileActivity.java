package dev.brkic.anniething.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import dev.brkic.anniething.R;
import dev.brkic.anniething.adapters.FirstPagePagerAdapter;
import dev.brkic.anniething.adapters.ProfileScreenSlidePagerAdapter;

public class ProfileActivity extends AppCompatActivity {
    private View mViewPager;
    private TextView summonerNameTW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        String summonerName = intent.getStringExtra("SummonerName");
        summonerNameTW = findViewById(R.id.summonerNameProfileTW);
        summonerNameTW.setText(summonerName);
        ProfileScreenSlidePagerAdapter sectionsPagerAdapter = new ProfileScreenSlidePagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.profile_view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.second_page_tabs);
        tabs.setupWithViewPager(viewPager);
    }
    private void initViews() {
        mViewPager = findViewById(R.id.profile_view_pager);
    }

}