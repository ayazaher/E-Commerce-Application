package com.zaher.aya.ecommerce_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Laptop extends AppCompatActivity {
    ListView mylist;
    ArrayAdapter<String> myadapt;
    EcommerceDBHelper ecommerce;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);

        mylist=(ListView)findViewById(R.id.listview);
        myadapt=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        mylist.setAdapter(myadapt);
        ecommerce=new EcommerceDBHelper(getApplicationContext());

        cursor=ecommerce.getproducts(getIntent().getExtras().getInt("CatID"));
        while(!cursor.isAfterLast()){
            myadapt.add(cursor.getString(1));
            cursor.moveToNext();
        }
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){

                    Intent i=new Intent(Laptop.this,Product_Details.class);


                    cursor.moveToPosition(position);
                    i.putExtra("id",cursor.getInt(0));
                    startActivity(i);
                }
                else {
                    Intent i=new Intent(Laptop.this, Product_Details.class);


                    cursor.moveToPosition(position);
                    i.putExtra("id",cursor.getInt(1));
                    startActivity(i);
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.laptop,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.laptops_search)
        {
            Intent i=new Intent(Laptop.this,laptop_search.class);
            startActivity(i);
            return  true;
        }
        else
        return super.onOptionsItemSelected(item);
    }
}
