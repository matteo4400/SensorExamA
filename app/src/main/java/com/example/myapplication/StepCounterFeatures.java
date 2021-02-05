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

public class StepCounterFeatures extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "myApplication.MESSAGE";

    SensorManager sensormanager;
    TextView featureslist;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter_features);
        Intent intent = getIntent();
        int sensor_index = Integer.parseInt(intent.getStringExtra(StepCounterSensors.EXTRA_MESSAGE));
        sensormanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> StepCounterList = sensormanager.getSensorList(Sensor.TYPE_STEP_COUNTER);
        featureslist = findViewById(R.id.stepcounterfeatureslist);
        featureslist.setText("Sensor name: " + StepCounterList.get(sensor_index).getName() + "\n"
                + "Sensor type : " + StepCounterList.get(sensor_index).getStringType() + "\n"
                + "Sensor vendor : " + StepCounterList.get(sensor_index).getVendor() + "\n"
                + "Sensor version : " + StepCounterList.get(sensor_index).getVersion() + "\n"
                + "Sensor resolution : " + StepCounterList.get(sensor_index).getResolution() + "\n"
                + "Sensor Maximum Range : " + StepCounterList.get(sensor_index).getMaximumRange() + "\n"
                + "Sensor Power Requirements : " + StepCounterList.get(sensor_index).getPower());
    }

    public void OpenDisplayStepCounterValue(View view) {
        Intent intent_sender = getIntent();
        final String index_to_send = intent_sender.getStringExtra(StepCounterSensors.EXTRA_MESSAGE);
        Intent intent = new Intent(this, DisplayStepCounterValue.class);
        intent.putExtra(EXTRA_MESSAGE, index_to_send);
        startActivity(intent);
    }
}