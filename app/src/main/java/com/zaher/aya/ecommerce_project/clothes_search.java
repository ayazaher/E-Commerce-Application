package com.zaher.aya.ecommerce_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class clothes_search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_search);
        ViewPager v=findViewById(R.id.viewpager);
        v.setAdapter(new SimpleClothes(getSupportFragmentManager()));
        TabLayout tab=findViewById(R.id.tab);
        tab.setupWithViewPager(v);
    }
}
