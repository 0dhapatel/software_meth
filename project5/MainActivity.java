package com.example.museum;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView lv = (ListView) findViewById(R.id.lv);
        final Button btn = (Button) findViewById(R.id.btn);

        String[] fruits = new String[] {
                "Cape Gooseberry",
                "Capuli cherry"
        };
        final List<String> fruits_list = new ArrayList<String>(Arrays.asList(fruits));

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, fruits_list);

        // DataBind ListView with items from ArrayAdapter
        lv.setAdapter(arrayAdapter);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                
            }

            @Override
            public void onClick(AdapterView<?> parent, View view,
                                int position, long id) {
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) List.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), Pricing.class);
                startActivity(intent);
            }
        });
    }
}

