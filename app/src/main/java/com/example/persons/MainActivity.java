package com.example.persons;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ListView listView;
    private ArrayList<String> listItem;
    private ArrayAdapter adapter;
    private String selectedPersonName = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);
        listItem = new ArrayList<>();
        viewData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedPerson = listItem.get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("PERSON_NAME", selectedPerson);
                startActivity(intent);
            }
        });


        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddPersonActivity.class));
            }
        });

        Button exportButton = findViewById(R.id.exportButton);
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportDataToCSV();  // Your existing method to export data
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePerson(selectedPersonName);
                viewData(); // Refresh the list
            }
        });
    }

    private void viewData() {
        Cursor cursor = dbHelper.viewData();
        listItem.clear();

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                String surname = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SURNAME));
                listItem.add(name + " " + surname);
            }
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
        listView.setAdapter(adapter);
        cursor.close();
    }

    private void deletePerson(String nameSurname) {
        if (!nameSurname.isEmpty()) {
            String[] parts = nameSurname.split(" ");
            String name = parts[0];
            String surname = parts.length > 1 ? parts[1] : "";
            dbHelper.deletePerson(name, surname);
        }
    }

    private void exportDataToCSV() {
        // Implement the logic to export data to CSV
    }


    @Override
    protected void onResume() {
        super.onResume();
        viewData(); // Refresh the list when returning to the activity
    }
}
