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

public class ProximityFeatures extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "myApplication.MESSAGE";

    SensorManager sensormanager;
    TextView featureslist;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_features);
        Intent intent = getIntent();
        int sensor_index = Integer.parseInt(intent.getStringExtra(GeomagneticRotation.EXTRA_MESSAGE));
        sensormanager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> ProximityList = sensormanager.getSensorList(Sensor.TYPE_PROXIMITY);
        featureslist = findViewById(R.id.proximityfeatureslist);
        featureslist.setText("Sensor name: " + ProximityList.get(sensor_index).getName() + "\n"
                + "Sensor type : " + ProximityList.get(sensor_index).getStringType() + "\n"
                + "Sensor vendor : " + ProximityList.get(sensor_index).getVendor() + "\n"
                + "Sensor version : " + ProximityList.get(sensor_index).getVersion() + "\n"
                + "Sensor resolution : " + ProximityList.get(sensor_index).getResolution() + "\n"
                + "Sensor Maximum Range : " + ProximityList.get(sensor_index).getMaximumRange() + "\n"
                + "Sensor Power Requirements : " + ProximityList.get(sensor_index).getPower() );
    }

    public void OpenDisplayProximityValue(View view) {
        Intent intent_sender = getIntent();
        final String index_to_send = intent_sender.getStringExtra(GeomagneticRotation.EXTRA_MESSAGE);
        Intent intent = new Intent(this, DisplayProximityValue.class);
        intent.putExtra(EXTRA_MESSAGE, index_to_send );
        startActivity(intent);
    }
}