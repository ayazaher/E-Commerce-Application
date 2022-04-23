package com.zaher.aya.ecommerce_project;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SimpleClothes extends FragmentPagerAdapter {
    public SimpleClothes(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return new ClothesTextFragment();
        else if(position==1)
            return new ClothesVoiceFragment();
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0)
            return "text";
        else if(position==1)
            return "voice";
        return super.getPageTitle(position);
    }
}
