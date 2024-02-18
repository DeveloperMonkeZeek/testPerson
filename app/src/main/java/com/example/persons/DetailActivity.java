package com.example.persons;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextView detailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dbHelper = new DatabaseHelper(this);
        detailsTextView = findViewById(R.id.detailsTextView);

        // Retrieve the name of the person passed from MainActivity
        String personName = getIntent().getStringExtra("PERSON_NAME");
        if (personName != null) {
            displayPersonDetails(personName);
        } else {
            detailsTextView.setText("No person data received.");
        }
    }

    private void displayPersonDetails(String personName) {
        Cursor cursor = dbHelper.getPersonDetails(personName);
        if (cursor != null && cursor.moveToFirst()) {
            int surnameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SURNAME);
            int phoneIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE);
            int addressIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ADDRESS);
            int workplaceIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_WORKPLACE);

            String surname = surnameIndex != -1 ? cursor.getString(surnameIndex) : "Not available";
            String phone = phoneIndex != -1 ? cursor.getString(phoneIndex) : "Not available";
            String address = addressIndex != -1 ? cursor.getString(addressIndex) : "Not available";
            String workplace = workplaceIndex != -1 ? cursor.getString(workplaceIndex) : "Not available";

            String details = "Name: " + personName + "\nSurname: " + surname + "\nPhone: " + phone + "\nAddress: " + address + "\nWorkplace: " + workplace;
            detailsTextView.setText(details);
        } else {
            detailsTextView.setText("Details not found for: " + personName);
        }
        if (cursor != null) {
            cursor.close();
        }
    }
}
