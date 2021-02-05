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

public class LinearAccelerationFeatures extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "myApplication.MESSAGE";

    SensorManager sensormanager;
    TextView featureslist;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_acceleration_features);
        Intent intent = getIntent();
        int sensor_index = Integer.parseInt(intent.getStringExtra(LinearAccelerationSensors.EXTRA_MESSAGE));
        sensormanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> LinearAccelerationList = sensormanager.getSensorList(Sensor.TYPE_LINEAR_ACCELERATION);
        featureslist = findViewById(R.id.linearaccelerationfeatureslist);
        featureslist.setText("Sensor name: " + LinearAccelerationList.get(sensor_index).getName() + "\n"
                + "Sensor type : " + LinearAccelerationList.get(sensor_index).getStringType() + "\n"
                + "Sensor vendor : " + LinearAccelerationList.get(sensor_index).getVendor() + "\n"
                + "Sensor version : " + LinearAccelerationList.get(sensor_index).getVersion() + "\n"
                + "Sensor resolution : " + LinearAccelerationList.get(sensor_index).getResolution() + "\n"
                + "Sensor Maximum Range : " + LinearAccelerationList.get(sensor_index).getMaximumRange() + "\n"
                + "Sensor Power Requirements : " + LinearAccelerationList.get(sensor_index).getPower());
    }

    public void OpenDisplayLinearAccelerationValue(View view) {
        Intent intent_sender = getIntent();
        final String index_to_send = intent_sender.getStringExtra(LinearAccelerationSensors.EXTRA_MESSAGE);
        Intent intent = new Intent(this, DisplayLinearAccelerationValue.class);
        intent.putExtra(EXTRA_MESSAGE, index_to_send);
        startActivity(intent);
    }
}