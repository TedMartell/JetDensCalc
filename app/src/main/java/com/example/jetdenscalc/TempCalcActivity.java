package com.example.jetdenscalc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class TempCalcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_calc);

        Button calcTempButton = findViewById(R.id.calcTempDensityButton);
        calcTempButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            public void onClick(View v) {
                EditText currentTempInput = findViewById(R.id.currentTempInput);
                EditText currentBatchInput = findViewById(R.id.currentBatchInput);

                // Add TextWatcher to currentTempInput for validation
                currentTempInput.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        // No action needed here
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        // No action needed here
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        validateCurrentTempInput(currentTempInput, s.toString());
                    }
                });

                // Add TextWatcher to currentBatchInput for validation
                currentBatchInput.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        // No action needed here
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        // No action needed here
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        validateCurrentBatchInput(currentBatchInput, s.toString());
                    }
                });

                // Perform final calculation if both inputs are valid
                if (currentTempInput.getError() == null && currentBatchInput.getError() == null) {
                    float currentBatchTemp = Float.parseFloat(currentTempInput.getText().toString());
                    float currentBatchDensity = Float.parseFloat(currentBatchInput.getText().toString());

                    // Perform the calculation and display the result
                    final double ALPHA = 0.0006;
                    final byte refTemp = 15;
                    double densityCurrentTemp = currentBatchDensity * (1 - ALPHA * (currentBatchTemp - refTemp));
                    TextView resultCalcTempDensity = findViewById(R.id.resultCalcTempDensity);
                    resultCalcTempDensity.setText(String.format("%.2f", densityCurrentTemp));
                } else {
                    Toast.makeText(TempCalcActivity.this, "Please correct the input values", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Validation methods outside the button click listener
    private void validateCurrentTempInput(EditText inputEditText, String input) {
        try {
            float value = Float.parseFloat(input);
            // Validate range between -10 and +50
            if (value < -10 || value > 50) {
                inputEditText.setError("Value must be between -10 and 50");
            } else {
                inputEditText.setError(null);
            }
        } catch (NumberFormatException e) {
            inputEditText.setError("Invalid input");
        }
    }

    private void validateCurrentBatchInput(EditText inputEditText, String input) {
        try {
            float value = Float.parseFloat(input);
            // Validate range between 775 and 840
            if (value < 775 || value > 840) {
                inputEditText.setError("Value must be between 775 and 840");
            } else {
                inputEditText.setError(null);
            }
        } catch (NumberFormatException e) {
            inputEditText.setError("Invalid input");
        }
    }
}
