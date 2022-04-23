package com.zaher.aya.ecommerce_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button login;
    EditText email;
    String ema;
    EditText password;
    String pass;
    TextView forgot;
    private CheckBox checkb;
    private SharedPreferences mypref;
    public static String pref_name="PrefsFile";


    public static String loginName = "pref_name";
    public static String loginPass ="pref_pass" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

            mypref=getSharedPreferences(pref_name,MODE_PRIVATE);
            bindwidget();
            getprefdata();
            final EcommerceDBHelper ecomm=new EcommerceDBHelper(this);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ema=email.getText().toString();
                    pass=password.getText().toString();


                    Boolean check=ecomm.email_password(ema,pass);
                    if (checkb.isChecked()){
                        Boolean ischecked=checkb.isChecked();
                        SharedPreferences.Editor editor=mypref.edit();
                        editor.putString("pref_name",email.getText().toString());
                        editor.putString("pref_pass",password.getText().toString());
                        editor.putBoolean("pref_check",ischecked);
                        editor.apply();
                    }
                    else{
                        mypref.edit().clear().apply();

                    }
                    if (check==true){
                        Intent i=new Intent(Login.this, Ecommerce.class);
                        startActivity(i);
                        email.getText().clear();
                        password.getText().clear();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"E-mail Or Password are Wrong",Toast.LENGTH_LONG).show();
                    }
                }
            });


            forgot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(Login.this,Forgot.class);
                    startActivity(i);
                }
            });

        }

        private void getprefdata() {
            SharedPreferences sp=getSharedPreferences(pref_name,MODE_PRIVATE);
            if(sp.contains("pref_name")){
                String u=sp.getString("pref_name","not found");
                email.setText(u.toString());
            }

            if(sp.contains("pref_pass")){
                String p=sp.getString("pref_pass","not found");
                password.setText(p.toString());
            }

            if(sp.contains("pref_check")){
                Boolean b=sp.getBoolean("pref_check",false);
                checkb.setChecked(b);
            }



        }

        private void bindwidget(){
            email=(EditText)findViewById(R.id.email_txt);
            password=(EditText)findViewById(R.id.password_txt);
            login =(Button)findViewById(R.id.login_btn);
            checkb=(CheckBox)findViewById(R.id.checkBox);
            forgot=(TextView)findViewById(R.id.forgot) ;


        }


}
