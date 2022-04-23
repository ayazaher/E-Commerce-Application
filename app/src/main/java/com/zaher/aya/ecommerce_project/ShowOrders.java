package com.zaher.aya.ecommerce_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowOrders extends AppCompatActivity {
    SharedPreferences sp;
    int CustomerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_orders);
        ListView Orders = (ListView)findViewById(R.id.listview);
        TextView n=(TextView)findViewById(R.id.textView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,new ArrayList<String>());
        Orders.setAdapter(adapter);
        EcommerceDBHelper ecommerce = new EcommerceDBHelper(getApplicationContext());
        sp = getSharedPreferences(Login.pref_name,MODE_PRIVATE);

        final  String _Username = sp.getString(Login.loginName,"");
        final String _Password = sp.getString(Login.loginPass,"");
         CustomerID = ecommerce.getCustID(_Username,_Password);

        Cursor cursor = ecommerce.fetchallOrders(CustomerID);
        if(cursor!=null)
        {
            do{
                String Order_id = cursor.getString(cursor.getColumnIndex("OrdID"));
                adapter.add("Order #"+Order_id);
            }while (cursor.moveToNext());
        }

    }
}
