package com.zaher.aya.ecommerce_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Ecommerce extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecommerce);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shopping,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if(id==R.id.category)
        {
            Intent c=new Intent(Ecommerce.this,Categories.class);
            startActivity(c);
            return true;
        }
        else if(id==R.id.cart)
        {
            Intent sc=new Intent(Ecommerce.this,Shopping_cart.class);
            startActivity(sc);
            return true;
        }
        else if(id==R.id.logout)
        {
            Intent l=new Intent(Ecommerce.this,Login.class);
            startActivity(l);
            return true;

        }

        return false;
    }


}
