package com.example.jetdenscalc;

import java.util.Scanner;

public class CalcDensity {
    public static <string> void calcTempDensity() {
        final double ALPHA = 0.0006;
        final byte refTemp = 15;
        float currentBatchTemp;
        float currentBatchDensity;


        Scanner scanner = new Scanner(System.in);
        // 1. ask for input "currentBatchTemp"
        while (true) {
            System.out.print("Enter fuel Temperature: ");
            currentBatchTemp = scanner.nextFloat();
            if (currentBatchTemp >= -10 && currentBatchTemp <= 50)
                break;
            System.out.println("Enter a value between -10 and +50");
        }
        // 2. ask for input "currentBatchDensity"
        while (true) {
            System.out.print("Enter Batch Density at 15'C: ");
            currentBatchDensity = scanner.nextFloat();
            if (currentBatchDensity >= 775 && currentBatchDensity <= 840)
                break;
            System.out.println(" Enter a value between 775 and 840");
        }
        // 3. run calculation densityCurrentTemp
        double densityCurrentTemp = currentBatchDensity * (1-ALPHA * (currentBatchTemp - refTemp));
        // 4. display densityCurrentTemp
        System.out.print("Density is: " + String.format("%.2f", densityCurrentTemp) +" kg/m3 at " + currentBatchTemp + "'C");

    }

    public static void calcBatchDensity() {
        float currentFuelVolume;
        float addedFuelVolume;
        float currentFuelDensity;
        float addedFuelDensity;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter fuel Volume (litres): ");
            currentFuelVolume = scanner.nextFloat();
            if (currentFuelVolume >= 0 && currentFuelVolume <= 100_000)
                break;
            System.out.println("Enter a value between 0 and 100,000");
        }

        while (true) {
            System.out.print("Enter Batch Density at 15'C: ");
            currentFuelDensity = scanner.nextFloat();
            if (currentFuelDensity >= 775 && currentFuelDensity <= 840)
                break;
            System.out.println(" Enter a value between 775 and 840");
        }

        while (true) {
            System.out.print("Enter fuel Volume (litres): ");
            addedFuelVolume = scanner.nextFloat();
            if (addedFuelVolume >= 0 && addedFuelVolume <= 100_000)
                break;
            System.out.println("Enter a value between 0 and 100,000");
        }

        while (true) {
            System.out.print("Enter Batch Density at 15'C: ");
            addedFuelDensity = scanner.nextFloat();
            if (addedFuelDensity >= 775 && addedFuelDensity <= 840)
                break;
            System.out.println(" Enter a value between 775 and 840");
        }

        double newBatchDensity = ((currentFuelVolume * currentFuelDensity)+(addedFuelVolume * addedFuelDensity)) / (currentFuelVolume + addedFuelVolume);
        System.out.println("New density is: " + String.format("%.2f", newBatchDensity) + " kg/m3");





    }
}
