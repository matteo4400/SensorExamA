package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import androidx.annotation.RequiresApi;
import android.widget.TextView;
import java.util.List;

public class PressureFeatures extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "myApplication.MESSAGE";

    TextView featureslist;
    SensorManager sensormanager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure_features);
        Intent intent = getIntent();
        int sensor_index = Integer.parseInt(intent.getStringExtra(Pressure.EXTRA_MESSAGE));
        sensormanager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> PressureList = sensormanager.getSensorList(Sensor.TYPE_PRESSURE);
        featureslist = findViewById(R.id.pressurefeatureslist);
        featureslist.setText("Sensor name: " + PressureList.get(sensor_index).getName() + "\n"
                + "Sensor type : " + PressureList.get(sensor_index).getStringType() + "\n"
                + "Sensor vendor : " + PressureList.get(sensor_index).getVendor() + "\n"
                + "Sensor version : " + PressureList.get(sensor_index).getVersion() + "\n"
                + "Sensor resolution : " + PressureList.get(sensor_index).getResolution() + "\n"
                + "Sensor Maximum Range : " + PressureList.get(sensor_index).getMaximumRange() + "\n"
                + "Sensor Power Requirements : " + PressureList.get(sensor_index).getPower() );
    }

    public void OpenDisplayPressureValue(View view) {
        Intent intent_sender = getIntent();
        final String index_to_send = intent_sender.getStringExtra(Pressure.EXTRA_MESSAGE);
        Intent intent = new Intent(this, DisplayPressureValue.class);
        intent.putExtra(EXTRA_MESSAGE, index_to_send );
        startActivity(intent);
    }
}