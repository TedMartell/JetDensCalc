package com.example.jetdenscalc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BatchCalcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_batch_calc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @SuppressLint("DefaultLocale")
    public void calcBatchDensity(View v) {

        EditText currentBatchDensityInput = (EditText) findViewById(R.id.currFuelDensityInput);
        EditText addedBatchDensityInput = (EditText) findViewById(R.id.addedFuelDenistyInput);

        // Add TextWatcher to currentBatchDensityInput for validation
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
                validateCurrentBatchInput(currentBatchDensityInput, s.toString());
            }
        });

        // Add TextWatcher to addedBatchDensityInput for validation
        addedBatchDensityInput.addTextChangedListener(new TextWatcher() {
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
                validateAddedBatchInput(addedBatchDensityInput, s.toString());
            }
        });


        if (currentBatchDensityInput.getError() == null && addedBatchDensityInput.getError() == null) {
            float currentBatchDensity = Float.parseFloat(currentBatchDensityInput.getText().toString());
            float addedBatchDensity = Float.parseFloat(addedBatchDensityInput.getText().toString());

            // Perform the calculation and display the result
            TextView currentVolumeInput = (TextView) findViewById(R.id.currentFuelVolumeInput);
            float currentFuelVolume = Float.parseFloat(currentVolumeInput.getText().toString());

            TextView addedVolumeInput = (TextView) findViewById(R.id.addedFuelVolumeInput);
            float addedFuelVolume = Float.parseFloat(addedVolumeInput.getText().toString());

            TextView resultCalcTempDensity = (TextView) findViewById(R.id.resultCalcBatchDensity);
            double densityNewBatch = ((currentFuelVolume * currentBatchDensity) + (addedFuelVolume * addedBatchDensity)) / (currentFuelVolume + addedFuelVolume);
            resultCalcTempDensity.setText(String.format("%.2f", densityNewBatch));
        } else {
            Toast.makeText(BatchCalcActivity.this, "Please correct the input values", Toast.LENGTH_SHORT).show();
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

    private void validateAddedBatchInput(EditText inputEditText, String input) {
        try {
            float value = Float.parseFloat(input);
            // Validate range between 775 and 840
            if (value < 775 || value > 840) {
                inputEditText.setError("Value must be between 775 and 840");
            } else {
                inputEditText.setError(null);
            }
        } catch (NumberFormatException e) {
            inputEditText.setError("Invalid input");}
    }
}

