package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Build;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import androidx.annotation.RequiresApi;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class LightFeatures extends AppCompatActivity implements SensorEventListener {

    SensorManager sensormanager;
    TextView featureslist;

    TextView displayvalue;
    TextView tv;

    private Sensor Lightsensor;

    static final int read_block_size = 100;

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
        displayvalue = findViewById(R.id.displaylightvalue);
        Lightsensor = LightList.get(sensor_index);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lux_value = event.values[0];
        Intent intent = getIntent();
        int sensor_number = (Integer.parseInt(intent.getStringExtra(Light.EXTRA_MESSAGE)) + 1);
        displayvalue.setText("Sensor n°" + sensor_number + " value : " + lux_value + " lx" + "\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensormanager.registerListener(this, Lightsensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensormanager.unregisterListener(this);
    }

    public void BtnSafe(View view) {
        //Write text into file
        try {
            FileOutputStream fileout = openFileOutput("mylightvalue.txt", MODE_PRIVATE);
            OutputStreamWriter outputwriter = new OutputStreamWriter(fileout);
            outputwriter.write(displayvalue.getText().toString());
            outputwriter.close();

            //Display file save message
            Toast.makeText(getBaseContext(), "Valore Salvato Correttamente!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BtnRead(View view) {
        //Reading test from file
        try{
            FileInputStream filein = openFileInput("mylightvalue.txt");
            InputStreamReader inputread = new InputStreamReader(filein);

            char [] inputBuffer = new char[read_block_size];
            String s = "";
            int charRead;

            while((charRead = inputread.read(inputBuffer)) > 0) {
                //Char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            tv = findViewById(R.id.lightsavedvalue);
            inputread.close();
            tv.setText(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}