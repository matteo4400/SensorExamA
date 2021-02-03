package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import androidx.annotation.RequiresApi;
import android.widget.TextView;
import java.util.List;

public class LightFeatures extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "myApplication.MESSAGE";

    SensorManager sensormanager;
    TextView featureslist;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_features);
        Intent intent = getIntent();
        int sensor_index = Integer.parseInt(intent.getStringExtra(Light.EXTRA_MESSAGE));
        sensormanager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> LightList = sensormanager.getSensorList(Sensor.TYPE_LIGHT);
        featureslist = findViewById(R.id.lightfeatureslist);
        featureslist.setText("Sensor name: " + LightList.get(sensor_index).getName() + "\n"
                + "Sensor type : " + LightList.get(sensor_index).getStringType() + "\n"
                + "Sensor vendor : " + LightList.get(sensor_index).getVendor() + "\n"
                + "Sensor version : " + LightList.get(sensor_index).getVersion() + "\n"
                + "Sensor resolution : " + LightList.get(sensor_index).getResolution() + "\n"
                + "Sensor Maximum Range : " + LightList.get(sensor_index).getMaximumRange() + "\n"
                + "Sensor Power Requirements : " + LightList.get(sensor_index).getPower() );
    }

    public void OpenDisplayLightValue(View view) {
        Intent intent_sender = getIntent();
        final String index_to_send = intent_sender.getStringExtra(Light.EXTRA_MESSAGE);
        Intent intent = new Intent(this, DisplayLightValue.class);
        intent.putExtra(EXTRA_MESSAGE, index_to_send );
        startActivity(intent);
    }
}