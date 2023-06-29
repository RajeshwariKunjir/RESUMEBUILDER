package com.example.resumebuilder;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewResumeActivity extends AppCompatActivity {
    private TextView textViewName;
    private TextView textViewNationality;
    private TextView textViewAddress;
    private TextView textViewPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resume);

        // Initialize TextViews
        textViewName = findViewById(R.id.textViewName);
        textViewNationality = findViewById(R.id.textViewNationality);
        textViewAddress = findViewById(R.id.textViewAddress);
        textViewPhoneNumber = findViewById(R.id.textViewPhoneNumber);

        // Retrieve resume data from intent or database and set the TextViews
        String name = getIntent().getStringExtra("name");
        String nationality = getIntent().getStringExtra("nationality");
        String address = getIntent().getStringExtra("address");
        String phoneNumber = getIntent().getStringExtra("phone_number");

        textViewName.setText(name);
        textViewNationality.setText(nationality);
        textViewAddress.setText(address);
        textViewPhoneNumber.setText(phoneNumber);
    }
}
