package com.example.jetdenscalc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class refTempCalcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ref_temp_calc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Return Button method
        ImageButton returnButton = findViewById(R.id.refTempCalcReturnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate back to MainActivity
                Intent intent = new Intent(refTempCalcActivity.this, MainActivity.class);

                // Start MainActivity
                startActivity(intent);

                // Optional: finish the current activity if you don't want it in the back stack
                finish();
            }
        });


        // Find the EditText views and button
        EditText currentTempInput = findViewById(R.id.currTempInput);
        EditText currentBatchDensityInput = findViewById(R.id.currBatchDensityInput);
        Button calcTempButton = findViewById(R.id.refTempCalcResultButton);


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
        currentBatchDensityInput.addTextChangedListener(new TextWatcher() {
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
                validateCurrentBatchDensityInput(currentBatchDensityInput, s.toString());
            }
        });


        // Button click listener for calculation
        calcTempButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                // Check if both inputs are valid
                if (currentTempInput.getError() == null && currentBatchDensityInput.getError() == null) {
                    // Perform final calculation
                    float currentBatchTemp = Float.parseFloat(currentTempInput.getText().toString());
                    float densityCurrentTemp = Float.parseFloat(currentBatchDensityInput.getText().toString());
                    SharedPreferences sharedPreferences = getSharedPreferences("SettingsPrefs", MODE_PRIVATE);
                    float alpha = sharedPreferences.getFloat("alpha_value", 0.0009f); // Use default 0.0009f if not found
                    final byte refTemp = 15;
                    double currentBatchDensity = densityCurrentTemp / (1 - alpha * (currentBatchTemp - refTemp));
                    TextView resultCalcRefDensity = findViewById(R.id.refTempCalcResult);
                    resultCalcRefDensity.setText(String.format("%.2f", currentBatchDensity));
                } else {
                    // Inform the user of errors
                    Toast.makeText(refTempCalcActivity.this, "Please correct the input values", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Validation methods outside the button click listener
    private void validateCurrentTempInput(EditText inputEditText, String input) {
        try {
            float value = Float.parseFloat(input);
            // Validate range between -40 and +50
            if (value < -40 || value > 50) {
                inputEditText.setError("Value must be between -40 and 50");
            } else {
                inputEditText.setError(null);
            }
        } catch (NumberFormatException e) {
            inputEditText.setError("Invalid input");
        }
    }

    private void validateCurrentBatchDensityInput(EditText inputEditText, String input) {
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