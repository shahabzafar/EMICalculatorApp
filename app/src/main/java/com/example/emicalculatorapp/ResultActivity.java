package com.example.emicalculatorapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        double emiResult = getIntent().getDoubleExtra("EMI_RESULT", -1);
        Log.d("ResultActivity", "Received EMI: " + emiResult);

        TextView resultTextView = findViewById(R.id.resultTextView);
        if (emiResult != -1) {
            resultTextView.setText("Your EMI is: " + String.format("%.2f", emiResult));
        } else {
            resultTextView.setText("Error calculating EMI.");
        }

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }
}
