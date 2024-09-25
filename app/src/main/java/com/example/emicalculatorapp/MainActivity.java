package com.example.emicalculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText mortgageAmountEditText;
    private EditText interestRateEditText;
    private EditText tenureEditText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mortgageAmountEditText = findViewById(R.id.mortgageAmount);
        interestRateEditText = findViewById(R.id.interestRate);
        tenureEditText = findViewById(R.id.tenure);
        calculateButton = findViewById(R.id.calculateButton);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calculateButton.setOnClickListener(v -> {
            String mortgageAmountStr = mortgageAmountEditText.getText().toString();
            String interestRateStr = interestRateEditText.getText().toString();
            String tenureStr = tenureEditText.getText().toString();

            double mortgageAmount = Double.parseDouble(mortgageAmountStr);
            double interestRate = Double.parseDouble(interestRateStr) / 100 / 12;
            int tenure = Integer.parseInt(tenureStr) * 12;

            double emi = calculateEMI(mortgageAmount, interestRate, tenure);
            Log.d("MainActivity", "Calculated EMI: " + emi);

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("EMI_RESULT", emi);
            startActivity(intent);
        });
    }

    private double calculateEMI(double principal, double rate, int term) {
        return (principal * rate * Math.pow(1 + rate, term)) / (Math.pow(1 + rate, term) - 1);
    }
}
