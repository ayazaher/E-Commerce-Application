package com.zaher.aya.ecommerce_project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Forgot extends AppCompatActivity {
    EditText email;
    TextView pass;
    Button forgot;
    EcommerceDBHelper ecommerce;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        email=(EditText)findViewById(R.id.email_txt);
        pass=(TextView) findViewById(R.id.pass_txt);
        forgot=(Button)findViewById(R.id.forgot_btn);
        ecommerce=new EcommerceDBHelper(this);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password=ecommerce.forgotpass(email.getText().toString());
                pass.setText(password);
            }
        });
    }
}
