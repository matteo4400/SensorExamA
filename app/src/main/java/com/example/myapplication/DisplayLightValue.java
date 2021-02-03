package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.view.View;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class DisplayLightValue extends AppCompatActivity implements SensorEventListener {

    TextView displayvalue;
    TextView tv;

    private SensorManager sensormanager;
    private Sensor Lightsensor;

    static final int read_block_size = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_light_value);
        displayvalue = findViewById(R.id.displaylightvalue);
        Intent intent = getIntent();
        int light_sensor_index = Integer.parseInt(intent.getStringExtra(LightFeatures.EXTRA_MESSAGE));
        sensormanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> LightList = sensormanager.getSensorList(Sensor.TYPE_LIGHT);
        Lightsensor = LightList.get(light_sensor_index);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lux_value = event.values[0];
        displayvalue.setText("Sensor value : " + lux_value + "\n");
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