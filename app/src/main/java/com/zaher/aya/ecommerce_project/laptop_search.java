package com.zaher.aya.ecommerce_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;

public class laptop_search extends AppCompatActivity {

    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop_search);
        ViewPager v=findViewById(R.id.viewpager);
         v.setAdapter(new SimpleLaptop(getSupportFragmentManager()));
        TabLayout tab=findViewById(R.id.tab);
        tab.setupWithViewPager(v);

    }
}
