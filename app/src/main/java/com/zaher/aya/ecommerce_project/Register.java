package com.zaher.aya.ecommerce_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;


public class Register extends AppCompatActivity {
    EcommerceDBHelper ecomm;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText custname=(EditText)findViewById(R.id.name_txt);
        final EditText email=(EditText)findViewById(R.id.email_txt);
        final EditText pass=(EditText)findViewById(R.id.password_txt);
        final EditText confirm=(EditText)findViewById(R.id.con_txt);
        final RadioGroup gender=(RadioGroup) findViewById(R.id.radioGroup);
        final EditText calend=(EditText) findViewById(R.id.cal_txt);
        final EditText job=(EditText)findViewById(R.id.job_txt);

        ecomm=new EcommerceDBHelper(this);

        calend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int m = cal.get(Calendar.MONTH);
                int d = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(Register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calend.setText(i2+"-"+i1+"-"+i);
                        cal.set(i,i1,i2);

                    }
                }, year, m, d);

                datePickerDialog.show();
            }

        });



        Button butsign=(Button)findViewById(R.id.SignUp_btn);
        butsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String custn=custname.getText().toString();
                String emai=email.getText().toString();
                String passw=pass.getText().toString();
                String con=confirm.getText().toString();
                String cale= calend.getText().toString();
                String jo=job.getText().toString();
                int select=gender.getCheckedRadioButtonId();
                String gender;
                if (select==R.id.female)
                    gender = "Female";
                else
                    gender = "Male";



                if (custn.equals("")||emai.equals("")||passw.equals("")||con.equals("")||jo.equals("")){
                    Toast.makeText(getApplicationContext(),"You Must Enter All Data",Toast.LENGTH_LONG).show();
                }
                else{
                    if(passw.equals(con)){
                        Boolean check=ecomm.checkemai(emai);
                        if (check==true){
                            Boolean insert=ecomm.insert(custn,emai,passw,gender,cale,jo);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"E-mail exists",Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Password Not Match",Toast.LENGTH_LONG).show();
                    }
                }


            }
        });





    }
    }

