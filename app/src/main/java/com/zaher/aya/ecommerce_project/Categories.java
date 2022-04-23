package com.zaher.aya.ecommerce_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Categories extends AppCompatActivity {
    ListView mylist;
    ArrayAdapter<String> myadapt;
    EcommerceDBHelper ecommerce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        mylist=(ListView)findViewById(R.id.listview);
        myadapt=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        mylist.setAdapter(myadapt);
        ecommerce=new EcommerceDBHelper(getApplicationContext());
        Cursor cursor=ecommerce.getallcateg();
        while(!cursor.isAfterLast()){
            myadapt.add(cursor.getString(1));
            cursor.moveToNext();
        }
      mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v=((TextView)view);
                String catname=v.getText().toString();
                int cat_id=ecommerce.get_cat_id(catname);

                Intent i=new Intent(Categories.this,Laptop.class);
                    i.putExtra("CatID",cat_id);

                    startActivity(i);



            }
        });


    }
}
