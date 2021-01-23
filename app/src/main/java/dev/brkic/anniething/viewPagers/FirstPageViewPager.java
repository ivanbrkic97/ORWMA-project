package dev.brkic.anniething.viewPagers;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import dev.brkic.anniething.R;

public class FirstPageViewPager extends AppCompatActivity {
    private View mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews() {
        mViewPager = findViewById(R.id.first_page_view_pager);
    }

}
