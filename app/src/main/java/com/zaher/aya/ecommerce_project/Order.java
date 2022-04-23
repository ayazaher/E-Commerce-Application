package com.zaher.aya.ecommerce_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Order extends AppCompatActivity {
    TextView OrderID;
    TextView OrderDate;
    TextView Address;
    Button Checkout;
    Button locate;
    TextView Location;
    int CustomerID;
    EcommerceDBHelper ecommerceDB;
    SharedPreferences sp;
    Calendar calendar;
    ArrayList<Product> productsArrayList;
    ProductSeraizable productSerializable;
    TextView q;
    EditText qu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        final int prodid=getIntent().getExtras().getInt("id");

        OrderID = (TextView)findViewById(R.id.tvOrderID);
        OrderDate=(TextView)findViewById(R.id.tvOrderDate);
        Checkout = (Button)findViewById(R.id.btCheckout);
        q=(TextView)findViewById(R.id.textView16) ;
        qu=(EditText)findViewById(R.id.qant) ;

        locate = (Button)findViewById(R.id.btLocation);
        Location = (TextView)findViewById(R.id.tvLocation);
        ecommerceDB = new EcommerceDBHelper(Order.this);
        sp = getSharedPreferences(Login.pref_name,MODE_PRIVATE);

       final  String _Username = sp.getString(Login.loginName,"");
       final String _Password = sp.getString(Login.loginPass,"");

        calendar = Calendar.getInstance();
        OrderID.setText(String.valueOf(ecommerceDB.getLastOrdID()+1));
        OrderDate.setText(calendar.getTime().toString());
        Checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = calendar.getTime().toString();
               CustomerID = ecommerceDB.getCustID(_Username,_Password);

                ecommerceDB.addOrders(date,CustomerID,String.valueOf(Location.getText()));
                int qa=Integer.parseInt(qu.getText().toString());
                int lastOrdId = ecommerceDB.getLastOrdID();
                ecommerceDB.OrderDetails(prodid,lastOrdId,qa);
                int quantity=ecommerceDB.quant(prodid);
                quantity-=qa;
                ecommerceDB.subtractQuantity(prodid,quantity);
                Toast.makeText(getApplicationContext(),"Order Submitted",Toast.LENGTH_LONG).show();
            }
        });



    }
}
