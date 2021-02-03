package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class EnvironmentalSensors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental_sensors);
    }

    public void OpenAmbientTemperatureSensors(View view) {
        Intent intent = new Intent(this, AmbientTemperature.class);
        startActivity(intent);
    }

    public void OpenLightSensors(View view) {
        Intent intent = new Intent(this, Light.class);
        startActivity(intent);
    }

    public void OpenPressureSensors(View view) {
        Intent intent = new Intent(this, Pressure.class);
        startActivity(intent);
    }

    public void OpenRelativeHumiditySensors(View view) {
        Intent intent = new Intent(this, RelativeHumidity.class);
        startActivity(intent);
    }

    public void OpenSelectSavedValue(View view) {
        Intent intent = new Intent(this, EnvironmentSelectSavedValue.class);
        startActivity(intent);
    }
}