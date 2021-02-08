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

import com.example.myapplication.Proximity;
import com.example.myapplication.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class ProximityFeatures extends AppCompatActivity implements SensorEventListener {

    SensorManager sensormanager;
    TextView featureslist;

    TextView displayvalue;
    TextView tv;

    private Sensor Proximitysensor;

    static final int read_block_size = 100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_features);
        Intent intent = getIntent();
        int sensor_index = Integer.parseInt(intent.getStringExtra(AmbientTemperature.EXTRA_MESSAGE));
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
        displayvalue = findViewById(R.id.displayproximityvalue);
        Proximitysensor = ProximityList.get(sensor_index);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float proximity_value = event.values[0];
        Intent intent = getIntent();
        int sensor_number = (Integer.parseInt(intent.getStringExtra(Light.EXTRA_MESSAGE)) + 1);
        displayvalue.setText("Sensor n° " + sensor_number + "value : " + proximity_value + "\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensormanager.registerListener(this, Proximitysensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensormanager.unregisterListener(this);
    }

    public void BtnSafe(View view) {
        //Write text into file
        try {
            FileOutputStream fileout = openFileOutput("myproximityvalue.txt", MODE_PRIVATE);
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
            FileInputStream filein = openFileInput("myproximityvalue.txt");
            InputStreamReader inputread = new InputStreamReader(filein);

            char [] inputBuffer = new char[read_block_size];
            String s = "";
            int charRead;

            while((charRead = inputread.read(inputBuffer)) > 0) {
                //Char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            tv = findViewById(R.id.proximitysavedvalue);
            inputread.close();
            tv.setText(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}