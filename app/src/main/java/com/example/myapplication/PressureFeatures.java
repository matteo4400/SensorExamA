package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Build;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
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

public class PressureFeatures extends AppCompatActivity implements SensorEventListener {

    SensorManager sensormanager;
    TextView featureslist;

    TextView displayvalue;
    TextView tv;

    private Sensor Pressuresensor;

    static final int read_block_size = 100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure_features);
        Intent intent = getIntent();
        int sensor_index = Integer.parseInt(intent.getStringExtra(Pressure.EXTRA_MESSAGE));
        sensormanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> PressureList = sensormanager.getSensorList(Sensor.TYPE_PRESSURE);
        featureslist = findViewById(R.id.pressurefeatureslist);
        featureslist.setText("Sensor name: " + PressureList.get(sensor_index).getName() + "\n"
                + "Sensor type : " + PressureList.get(sensor_index).getStringType() + "\n"
                + "Sensor vendor : " + PressureList.get(sensor_index).getVendor() + "\n"
                + "Sensor version : " + PressureList.get(sensor_index).getVersion() + "\n"
                + "Sensor resolution : " + PressureList.get(sensor_index).getResolution() + "\n"
                + "Sensor Maximum Range : " + PressureList.get(sensor_index).getMaximumRange() + "\n"
                + "Sensor Power Requirements : " + PressureList.get(sensor_index).getPower());
        displayvalue = findViewById(R.id.displaypressurevalue);
        Pressuresensor = PressureList.get(sensor_index);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float pressure_value = event.values[0];
        Intent intent = getIntent();
        int sensor_number = (Integer.parseInt(intent.getStringExtra(Light.EXTRA_MESSAGE)) + 1);
        displayvalue.setText("Sensor n°" + sensor_number + " value : " + pressure_value + " mbar" + "\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensormanager.registerListener(this, Pressuresensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensormanager.unregisterListener(this);
    }

    public void BtnSafe(View view) {
        //Write text into file
        try {
            FileOutputStream fileout = openFileOutput("mypressurevalue.txt", MODE_PRIVATE);
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
        try {
            FileInputStream filein = openFileInput("mypressurevalue.txt");
            InputStreamReader inputread = new InputStreamReader(filein);

            char[] inputBuffer = new char[read_block_size];
            String s = "";
            int charRead;

            while ((charRead = inputread.read(inputBuffer)) > 0) {
                //Char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            tv = findViewById(R.id.pressuresavedvalue);
            inputread.close();
            tv.setText(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}