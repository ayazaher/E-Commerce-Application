package com.zaher.aya.ecommerce_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Shoes extends AppCompatActivity {
    ListView mylist;
    ArrayAdapter<String> myadapt;
    EcommerceDBHelper ecommerce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes);
        mylist=(ListView)findViewById(R.id.listview);
        myadapt=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        mylist.setAdapter(myadapt);
        ecommerce=new EcommerceDBHelper(getApplicationContext());

        Cursor cursor=ecommerce.getproducts(getIntent().getExtras().getInt("CatID"));
        while(!cursor.isAfterLast()){
            myadapt.add(cursor.getString(1));
            cursor.moveToNext();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shoes,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if(id==R.id.shoes_search)
        {
            Intent i=new Intent(Shoes.this,shoes_search.class);
            startActivity(i);
            return true;
        }
        else
        return super.onOptionsItemSelected(item);
    }
}
