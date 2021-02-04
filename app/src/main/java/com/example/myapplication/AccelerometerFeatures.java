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

public class AccelerometerFeatures extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "myApplication.MESSAGE";

    SensorManager sensormanager;
    TextView featureslist;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_features);
        Intent intent = getIntent();
        int sensor_index = Integer.parseInt(intent.getStringExtra(AccelerometerSensors.EXTRA_MESSAGE));
        sensormanager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> AccelerometerList = sensormanager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        featureslist = findViewById(R.id.accelerometerfeatureslist);
        featureslist.setText("Sensor name: " + AccelerometerList.get(sensor_index).getName() + "\n"
                + "Sensor type : " + AccelerometerList.get(sensor_index).getStringType() + "\n"
                + "Sensor vendor : " + AccelerometerList.get(sensor_index).getVendor() + "\n"
                + "Sensor version : " + AccelerometerList.get(sensor_index).getVersion() + "\n"
                + "Sensor resolution : " + AccelerometerList.get(sensor_index).getResolution() + "\n"
                + "Sensor Maximum Range : " + AccelerometerList.get(sensor_index).getMaximumRange() + "\n"
                + "Sensor Power Requirements : " + AccelerometerList.get(sensor_index).getPower() );
    }

    public void OpenDisplayAccelerometerValue(View view) {
        Intent intent_sender = getIntent();
        final String index_to_send = intent_sender.getStringExtra(AccelerometerSensors.EXTRA_MESSAGE);
        Intent intent = new Intent(this, DisplayAccelerometerValue.class);
        intent.putExtra(EXTRA_MESSAGE, index_to_send );
        startActivity(intent);
    }
}