package com.example.jetdenscalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "SettingsPrefs";
    private static final String KEY_ALPHA = "alpha_value";
    private static final float DEFAULT_ALPHA = 0.0009f; // Default ALPHA value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        // Return Button method
        ImageButton returnButton = findViewById(R.id.returnSettingsButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate back to MainActivity
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);

                // Start MainActivity
                startActivity(intent);

                // Optional: finish the current activity if you don't want it in the back stack
                finish();
            }
        });



        EditText alphaInput = findViewById(R.id.alphaInput);
        Button saveButton = findViewById(R.id.saveButton);

        // Retrieve the saved ALPHA value from SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        float savedAlpha = prefs.getFloat(KEY_ALPHA, DEFAULT_ALPHA); // Use the default value if not found
        alphaInput.setText(String.valueOf(savedAlpha));

        // Save the new ALPHA value when the save button is clicked
        saveButton.setOnClickListener(v -> {
            try {
                // Parse the new ALPHA value from EditText
                float newAlpha = Float.parseFloat(alphaInput.getText().toString());

                // Save the new ALPHA value in SharedPreferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putFloat(KEY_ALPHA, newAlpha);
                editor.apply();

                // Optionally, show a Toast to indicate success
                Toast.makeText(SettingsActivity.this, "ALPHA value saved!", Toast.LENGTH_SHORT).show();

            } catch (NumberFormatException e) {
                // Handle invalid input if necessary
                Toast.makeText(SettingsActivity.this, "Invalid ALPHA value", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
