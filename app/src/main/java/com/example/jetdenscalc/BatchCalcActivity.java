package com.example.jetdenscalc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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


        TextView currentVolumeInput = (TextView) findViewById(R.id.currentFuelVolumeInput);
        float currentFuelVolume = Float.parseFloat(currentVolumeInput.getText().toString());

        TextView currentBatchDensityInput = (TextView) findViewById(R.id.currFuelDensityInput);
        float currentBatchDensity = Float.parseFloat(currentBatchDensityInput.getText().toString());

        TextView addedVolumeInput = (TextView) findViewById(R.id.addedFuelVolumeInput);
        float addedFuelVolume = Float.parseFloat(addedVolumeInput.getText().toString());

        TextView addedBatchDensityInput = (TextView) findViewById(R.id.addedFuelDenistyInput);
        float addedBatchDensity = Float.parseFloat(addedBatchDensityInput.getText().toString());

        TextView resultCalcTempDensity = (TextView) findViewById(R.id.resultCalcBatchDensity);
        double densityNewBatch = ((currentFuelVolume * currentBatchDensity) + (addedFuelVolume * addedBatchDensity)) / (currentFuelVolume + addedFuelVolume);
        resultCalcTempDensity.setText(String.format("%.2f", densityNewBatch));
    }

}