package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PositionSensor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_sensor);
    }

    public void OpenGeomagneticRotationSensors(View view) {
        Intent intent = new Intent(this, GeomagneticRotation.class);
        startActivity(intent);
    }

    public void OpenMagneticFieldSensors(View view) {
        Intent intent = new Intent(this, MagneticField.class);
        startActivity(intent);
    }

    public void OpenOrientationSensors(View view) {
        Intent intent = new Intent(this, Orientation.class);
        startActivity(intent);
    }

    public void OpenProximitySensors(View view) {
        Intent intent = new Intent(this, Proximity.class);
        startActivity(intent);
    }