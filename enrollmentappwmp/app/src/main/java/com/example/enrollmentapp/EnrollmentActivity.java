package com.example.enrollmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnrollmentActivity extends AppCompatActivity {
    private final ArrayList<String> selectedSubjects = new ArrayList<>();
    private int totalCredits = 0;
    private static final int MAX_CREDITS = 24;

    private final Map<String, Integer> subjects = new HashMap<String, Integer>() {{
        put("Software Engineering", 3);
        put("Linear Algebra", 2);
        put("Object Oriented and Visual Programming", 4);
        put("Probability and Statistics", 4);
        put("Server-Side Internet Programming", 3);
        put("Computer Network", 2);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);

        Spinner subjectSpinner = findViewById(R.id.spinnerSubjects);
        Button btnAddSubject = findViewById(R.id.btnAddSubject);
        Button btnViewSummary = findViewById(R.id.btnViewSummary);
        Button btnLogout = findViewById(R.id.btnLogout);
        TextView totalCreditsView = findViewById(R.id.textViewTotalCredits);

        // Populate the Spinner with subjects
        List<String> subjectList = new ArrayList<>(subjects.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjectList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter);

        // Add Subject Button
        btnAddSubject.setOnClickListener(view -> {
            String subject = (String) subjectSpinner.getSelectedItem();
            if (subject == null || !subjects.containsKey(subject)) {
                Toast.makeText(this, "Please select a valid subject.", Toast.LENGTH_SHORT).show();
                return;
            }

            int credits = subjects.get(subject);

            // Check if adding the subject exceeds the credit limit
            if (totalCredits + credits > MAX_CREDITS) {
                Toast.makeText(this, "Credit limit exceeded!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Add the subject to the selected list
            selectedSubjects.add(subject + " (" + credits + " credits)");
            totalCredits += credits;
            totalCreditsView.setText("Total Credits: " + totalCredits);
            Toast.makeText(this, "Subject added: " + subject, Toast.LENGTH_SHORT).show();
        });

        // View Summary Button
        btnViewSummary.setOnClickListener(view -> {
            Intent intent = new Intent(EnrollmentActivity.this, SummaryActivity.class);
            intent.putStringArrayListExtra("subjects", selectedSubjects);
            intent.putExtra("totalCredits", totalCredits);
            startActivity(intent);
        });

        // Logout Button
        btnLogout.setOnClickListener(view -> {
            Intent intent = new Intent(EnrollmentActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
