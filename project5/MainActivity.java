package com.example.museums;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listview);
        String[] museumName = new String[] {"Montclair Art Museum", "Insectropolis", "Hunterdon Art Museum", "Liberty Science Center"};
        List<String> museum_list = new ArrayList<>(Arrays.asList(museumName));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, museum_list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            Intent intent = new Intent(getApplicationContext(), Price.class);
            intent.putExtra("museum", selectedItem);
            startActivity(intent);
        });
    }
}
