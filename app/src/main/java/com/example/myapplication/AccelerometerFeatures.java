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

public class AccelerometerFeatures extends AppCompatActivity implements SensorEventListener {

    public final static String EXTRA_MESSAGE = "myApplication.MESSAGE";

    SensorManager sensormanager;
    TextView featureslist;

    TextView displayvalue;
    TextView tv;

    private Sensor Accelerometersensor;

    static final int read_block_size = 100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_features);
        Intent intent = getIntent();
        int sensor_index = Integer.parseInt(intent.getStringExtra(AccelerometerSensors.EXTRA_MESSAGE));
        sensormanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> AccelerometerList = sensormanager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        featureslist = findViewById(R.id.accelerometerfeatureslist);
        featureslist.setText("Sensor name: " + AccelerometerList.get(sensor_index).getName() + "\n"
                + "Sensor type : " + AccelerometerList.get(sensor_index).getStringType() + "\n"
                + "Sensor vendor : " + AccelerometerList.get(sensor_index).getVendor() + "\n"
                + "Sensor version : " + AccelerometerList.get(sensor_index).getVersion() + "\n"
                + "Sensor resolution : " + AccelerometerList.get(sensor_index).getResolution() + "\n"
                + "Sensor Maximum Range : " + AccelerometerList.get(sensor_index).getMaximumRange() + "\n"
                + "Sensor Power Requirements : " + AccelerometerList.get(sensor_index).getPower());
        displayvalue = findViewById(R.id.displayaccelerometervalue);
        Accelerometersensor = AccelerometerList.get(sensor_index);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float acceleration_value_x = event.values[0];
        float acceleration_value_y = event.values[1];
        float acceleration_value_z = event.values[2];
        displayvalue.setText("Sensor value on x axis"+acceleration_value_x+"\n"+
                "Sensor value on y axis : "+acceleration_value_y+"\n"+
                "Sensor value on z axis : "+acceleration_value_z+"\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensormanager.registerListener(this, Accelerometersensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensormanager.unregisterListener(this);
    }

    public void BtnSafe(View view) {
        //Write text into file
        try {
            FileOutputStream fileout = openFileOutput("myaccelerometervalue.txt", MODE_PRIVATE);
            OutputStreamWriter outputwriter = new OutputStreamWriter(fileout);
            outputwriter.write(displayvalue.getText().toString());
            outputwriter.close();

            //Display file save message
            Toast.makeText(getBaseContext(), "Valore Salvato Correttamente!", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BtnRead(View view) {
        //Reading test from file
        try{
            FileInputStream filein = openFileInput("myaccelerometervalue.txt");
            InputStreamReader inputread = new InputStreamReader(filein);

            char [] inputBuffer = new char[read_block_size];
            String s = "";
            int charRead;

            while((charRead = inputread.read(inputBuffer)) > 0) {
                //Char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            tv = findViewById(R.id.accelerometersavedvalue);
            inputread.close();
            tv.setText(s);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}