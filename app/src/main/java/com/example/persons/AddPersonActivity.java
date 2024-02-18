package com.example.persons;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddPersonActivity extends AppCompatActivity {

    private EditText nameEditText, surnameEditText, phoneEditText, addressEditText, workplaceEditText;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_persons);

        nameEditText = findViewById(R.id.nameEditText);
        surnameEditText = findViewById(R.id.surnameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        addressEditText = findViewById(R.id.addressEditText);
        workplaceEditText = findViewById(R.id.workplaceEditText);

        dbHelper = new DatabaseHelper(this);

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void saveData() {
        String name = nameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String workplace = workplaceEditText.getText().toString();

        dbHelper.insertData(name, surname, phone, address, workplace);
        finish();
    }
}
