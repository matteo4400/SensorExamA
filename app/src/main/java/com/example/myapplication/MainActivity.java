package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OpenEnvironmentalSensors(View view) {
        Intent intent = new Intent(this, EnvironmentalSensors.class);
        startActivity(intent);
    }

    public void OpenSelectTypeSavedValue(View view ) {
        Intent intent = new Intent(this, SelectTypeSavedValue.class);
        startActivity(intent);
    }
}