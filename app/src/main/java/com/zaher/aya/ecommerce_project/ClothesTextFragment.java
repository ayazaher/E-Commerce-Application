package com.zaher.aya.ecommerce_project;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.

 */
public class ClothesTextFragment extends Fragment {

    public ClothesTextFragment() {
        // Required empty public constructor
    }

    EcommerceDBHelper ecommerce;
    EditText name;
    ListView mylist;
    ArrayAdapter<String> myadapt;
    Cursor cursor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ecommerce = new EcommerceDBHelper(getActivity());
        View rootview=inflater.inflate(R.layout.fragment_clothes_text, container, false);
        name = (EditText) rootview.findViewById(R.id.name_txt);
        Button search = (Button) rootview.findViewById(R.id.search_btn);


        myadapt = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        mylist = (ListView) rootview.findViewById(R.id.listview);
        mylist.setAdapter(myadapt);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cursor = ecommerce.getpro(name.getText().toString(), 2);
                myadapt.clear();
                while (!cursor.isAfterLast()) {
                    myadapt.add(cursor.getString(1));
                    cursor.moveToNext();
                }
            }
        });


        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){

                    Intent i=new Intent(getActivity(),Product_Details.class);


                    cursor.moveToPosition(position);
                    i.putExtra("id",cursor.getInt(0));
                    startActivity(i);
                }
                else if (position==1){
                    Intent i=new Intent(getActivity(),Product_Details.class);


                    cursor.moveToPosition(position);
                    i.putExtra("id",cursor.getInt(1));
                    startActivity(i);
                }

            }
        });

        return rootview;
    }

}



