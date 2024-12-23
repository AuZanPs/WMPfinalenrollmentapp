package com.example.enrollmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        ArrayList<String> subjects = getIntent().getStringArrayListExtra("subjects");
        int totalCredits = getIntent().getIntExtra("totalCredits", 0);

        ListView listView = findViewById(R.id.listViewSubjects);
        TextView totalCreditsView = findViewById(R.id.textViewTotalCredits);

        if (subjects != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subjects);
            listView.setAdapter(adapter);
        }
        totalCreditsView.setText("Total Credits: " + totalCredits);
    }
}
