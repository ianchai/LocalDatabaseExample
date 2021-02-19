package com.lomaikai.localdatabaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editName, editPhone, editDate;
    Button btnInsert, btnUpdate, btnDelete, btnView;

    DBfunctions db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Linking UI elements
        editName=findViewById(R.id.editName);
        editDate=findViewById(R.id.editDate);
        editPhone=findViewById(R.id.editPhone);
        btnInsert=findViewById(R.id.btnInsert);
        btnDelete=findViewById(R.id.btnDelete);
        btnUpdate=findViewById(R.id.btnUpdate);

        // Linking the DB class
        db = new DBfunctions(this);

        // Function for insert button
        btnInsert.setOnClickListener(view -> {
            String name, date, phone;
            name = editName.getText().toString();
            date = editDate.getText().toString();
            phone = editPhone.getText().toString();
            // Insert the data
            Boolean checkInsertData = db.insertUserData(name,phone,date);
            // Check if successful
            if (checkInsertData)
                Toast.makeText(MainActivity.this, "Data has been inserted.", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Could not insert the data.", Toast.LENGTH_SHORT).show();
        });

        // Function for update button
        btnUpdate.setOnClickListener(view -> {
            String name, date, phone;
            name = editName.getText().toString();
            date = editDate.getText().toString();
            phone = editPhone.getText().toString();
            // Insert the data
            Boolean checkUpdateData = db.updateUserData(name,phone,date);
            // Check if successful
            if (checkUpdateData)
                Toast.makeText(MainActivity.this, "Data has been updated.", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Could not update the data.", Toast.LENGTH_SHORT).show();
        });

        // Function for delete button
        btnDelete.setOnClickListener(view -> {
            String name;
            name = editName.getText().toString();
            // Insert the data
            Boolean checkDeleteData = db.deleteUserData(name);
            // Check if successful
            if (checkDeleteData)
                Toast.makeText(MainActivity.this, "Data has been deleted.", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Could not delete the data.", Toast.LENGTH_SHORT).show();
        });
    }
}