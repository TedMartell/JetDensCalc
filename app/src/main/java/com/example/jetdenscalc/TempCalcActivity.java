package com.example.jetdenscalc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class TempCalcActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_calc);


        Button calcTempButton = (Button) findViewById(R.id.calcTempDensityButton);
        calcTempButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            public void onClick(View v) {
                final double ALPHA = 0.0006;
                final byte refTemp = 15;



                TextView currentTempInput = (TextView) findViewById(R.id.currentTempInput);
                float currentBatchTemp = Float.parseFloat(currentTempInput.getText().toString());



                TextView currentBatchInput = (TextView) findViewById(R.id.currentBatchInput);
                float currentBatchDensity = Float.parseFloat(currentBatchInput.getText().toString());



                TextView resultCalcTempDensity = (TextView) findViewById(R.id.resultCalcTempDensity);
                double densityCurrentTemp = currentBatchDensity * (1-ALPHA * (currentBatchTemp - refTemp));
                resultCalcTempDensity.setText(String.format("%.2f", densityCurrentTemp));




            }
        });
    }



}