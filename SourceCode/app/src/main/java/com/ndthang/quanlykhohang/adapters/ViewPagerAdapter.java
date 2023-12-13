package com.ndthang.quanlykhohang.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ndthang.quanlykhohang.Utilities;
import com.ndthang.quanlykhohang.activities.fragments.HomeFragment;
import com.ndthang.quanlykhohang.activities.fragments.ProductFragment;
import com.ndthang.quanlykhohang.activities.fragments.StatisticalFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public static final int HOME_FRAGMENT = 0;
    public static final int PRODUCT_FRAGMENT = 1;
    public static final int STATISTICAL_FRAGMENT = 2;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case PRODUCT_FRAGMENT:
                return new ProductFragment();
            case STATISTICAL_FRAGMENT:
                return new StatisticalFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return Utilities.COUNT_TAB;
    }
}
