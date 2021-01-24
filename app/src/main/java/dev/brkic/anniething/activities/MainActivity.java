package dev.brkic.anniething.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.google.android.material.tabs.TabLayout;

import dev.brkic.anniething.R;
import dev.brkic.anniething.adapters.FirstPagePagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        FirstPagePagerAdapter sectionsPagerAdapter = new FirstPagePagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.first_page_view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.first_page_tabs);
        tabs.setupWithViewPager(viewPager);
    }

    public void searchButtonOnClick(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        EditText editText = (EditText) findViewById(R.id.championSearchNameET);
        String message = editText.getText().toString();
        intent.putExtra("SummonerName", message);
        startActivity(intent);
    }
}