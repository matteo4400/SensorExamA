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

public class GravityFeatures extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "myApplication.MESSAGE";

    SensorManager sensormanager;
    TextView featureslist;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity_features);
        Intent intent = getIntent();
        int sensor_index = Integer.parseInt(intent.getStringExtra(GravitySensors.EXTRA_MESSAGE));
        sensormanager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> GravityList = sensormanager.getSensorList(Sensor.TYPE_GRAVITY);
        featureslist = findViewById(R.id.gravityfeatureslist);
        featureslist.setText("Sensor name: " + GravityList.get(sensor_index).getName() + "\n"
                + "Sensor type : " + GravityList.get(sensor_index).getStringType() + "\n"
                + "Sensor vendor : " + GravityList.get(sensor_index).getVendor() + "\n"
                + "Sensor version : " + GravityList.get(sensor_index).getVersion() + "\n"
                + "Sensor resolution : " + GravityList.get(sensor_index).getResolution() + "\n"
                + "Sensor Maximum Range : " + GravityList.get(sensor_index).getMaximumRange() + "\n"
                + "Sensor Power Requirements : " + GravityList.get(sensor_index).getPower() );
    }

    public void OpenDisplayGravityValue(View view) {
        Intent intent_sender = getIntent();
        final String index_to_send = intent_sender.getStringExtra(GravitySensors.EXTRA_MESSAGE);
        Intent intent = new Intent(this, DisplayGravityValue.class);
        intent.putExtra(EXTRA_MESSAGE, index_to_send );
        startActivity(intent);
    }
}