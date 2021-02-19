package com.lomaikai.localdatabaseexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
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
        btnView=findViewById(R.id.btnView);

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

        // Function for view all button
        btnView.setOnClickListener(view -> {
            // Get all the entries from the database
            Cursor res = db.viewAllData();
            // Check if the table is empty
            if (res.getCount()==0) {
                Toast.makeText(MainActivity.this, "The database is empty.", Toast.LENGTH_SHORT).show();
                return;
            }
            // Create a string buffer to show to the user
            StringBuffer buffer = new StringBuffer();
            // Arranging the details of the message to display to the user
            while(res.moveToNext()) {
                buffer.append("Name: "+res.getString(0)+"\n");
                buffer.append("Phone: "+res.getString(1)+"\n");
                buffer.append("Date of birth: "+res.getString(2)+"\n");
            }
            // Creating an alert dialog and showing the current user entries
            AlertDialog.Builder builder = new AlertDialog.Builder((MainActivity.this));
            builder.setCancelable(true);
            builder.setTitle("User entries");
            builder.setMessage(buffer.toString());
            builder.show();
        });
    }
}