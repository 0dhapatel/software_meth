package com.example.museums;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listview);
        final TextView textView = (TextView) findViewById(R.id.textview);
        String[] players = new String[] {"CR7", "Messi", "Hazard", "Neymar"};
        List<String> Players_list = new ArrayList<String>(Arrays.asList(players));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Players_list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                //textView.setText("The best football player is : " + selectedItem);
                Intent intent = new Intent(MainActivity.this, Price.class);
                intent.putExtra("museum", selectedItem);
                startActivity(intent);
            }
        });
    }
}
