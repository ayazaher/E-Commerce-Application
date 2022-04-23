package com.zaher.aya.ecommerce_project;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.

 */
public class ShoesVoiceFragment extends Fragment {

    public ShoesVoiceFragment() {
        // Required empty public constructor
    }
    TextView name;
    int voicecode=1;
    ListView mylist;
    EcommerceDBHelper ecommerce;
    Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_shoes_voice, container, false);
        ecommerce=new EcommerceDBHelper(getActivity());
        mylist=(ListView)rootview.findViewById(R.id.listvoice);
        name=(TextView) rootview.findViewById(R.id.name);
        Button voice=(Button) rootview.findViewById(R.id.voice);


        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(intent,voicecode);
            }
        });

        return rootview;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == voicecode && resultCode == getActivity().RESULT_OK) {
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            name.setText(text.get(0));
            ArrayAdapter<String> product = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
            mylist.setAdapter(product);
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
                        i.putExtra("id",cursor.getInt(0));
                        startActivity(i);
                    }

                }
            });

            cursor = ecommerce.getpro(text.get(0), 3);
            if (cursor != null) {
                while (!cursor.isAfterLast()) {
                    product.add(cursor.getString(1));
                    cursor.moveToNext();


                }
            } else {
                Toast.makeText(getActivity(), "no match pro", Toast.LENGTH_LONG).show();
            }

        }

    }


}
