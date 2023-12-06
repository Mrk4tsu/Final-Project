package com.ndthang.quanlykhohang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ndthang.quanlykhohang.adapters.ViewPagerAdapter;
import com.ndthang.quanlykhohang.helper.Utilities;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUI();

        setUpViewPager();
        actionAnonymous();
    }
    private void setUpViewPager(){
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case ViewPagerAdapter.HOME_FRAGMENT:
                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case ViewPagerAdapter.PRODUCT_FRAGMENT:
                        bottomNavigationView.getMenu().findItem(R.id.action_product).setChecked(true);
                        break;
                    case ViewPagerAdapter.STATISTICAL_FRAGMENT:
                        bottomNavigationView.getMenu().findItem(R.id.action_statistical).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void actionAnonymous(){
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home){
                    viewPager.setCurrentItem(ViewPagerAdapter.HOME_FRAGMENT);
                }
                if (item.getItemId() == R.id.action_product){
                    viewPager.setCurrentItem(ViewPagerAdapter.PRODUCT_FRAGMENT);
                }
                if (item.getItemId() == R.id.action_statistical){
                    viewPager.setCurrentItem(ViewPagerAdapter.STATISTICAL_FRAGMENT);
                }
                return true;
            }
        });
    }
    private void getUI(){
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_nav);
    }
}