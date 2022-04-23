package com.zaher.aya.ecommerce_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Product_Details extends AppCompatActivity {
    public  static ArrayList<String> pID = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        EcommerceDBHelper ecommerce=new EcommerceDBHelper(this);
        final int prodid=getIntent().getExtras().getInt("id");
        Cursor details=ecommerce.getproductdata(prodid);
        Button add=(Button)findViewById(R.id.button);
        Button cart=(Button)findViewById(R.id.button2);


        TextView name=(TextView)findViewById(R.id.name);
        TextView price=(TextView)findViewById(R.id.price);
        TextView quant=(TextView)findViewById(R.id.quant);


        name.setText(details.getString(1));
        price.setText(details.getString(2));
        quant.setText(details.getString(3));
      add.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i=new Intent(Product_Details.this,Order.class);
              i.putExtra("id",prodid);
              startActivity(i);
          }
      });
      cart.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i=new Intent(Product_Details.this,ShowOrders.class);
              i.putExtra("id",prodid);
              startActivity(i);
          }
      });
    }
}
