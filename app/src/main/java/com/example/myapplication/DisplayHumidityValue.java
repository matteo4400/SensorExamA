package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class DisplayHumidityValue extends AppCompatActivity implements SensorEventListener {

    TextView displayvalue;
    TextView tv;

    private SensorManager sensormanager;
    private Sensor Humiditysensor;

    static final int read_block_size = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_humidity_value);
        displayvalue = findViewById(R.id.displayhumidityvalue);
        Intent intent = getIntent();
        int humidity_sensor_index = Integer.parseInt(intent.getStringExtra(HumidityFeatures.EXTRA_MESSAGE));
        sensormanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> HumidityList = sensormanager.getSensorList(Sensor.TYPE_RELATIVE_HUMIDITY);
        Humiditysensor = HumidityList.get(humidity_sensor_index);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float humidity_value = event.values[0];
        displayvalue.setText("Sensor value : " + humidity_value + "\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensormanager.registerListener(this, Humiditysensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensormanager.unregisterListener(this);
    }

    public void BtnSafe(View view) {
        //Write text into file
        try {
            FileOutputStream fileout = openFileOutput("myhumidityvalue.txt", MODE_PRIVATE);
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
            FileInputStream filein = openFileInput("myhumidityvalue.txt");
            InputStreamReader inputread = new InputStreamReader(filein);

            char [] inputBuffer = new char[read_block_size];
            String s = "";
            int charRead;

            while((charRead = inputread.read(inputBuffer)) > 0) {
                //Char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            tv = findViewById(R.id.humiditysavedvalue);
            inputread.close();
            tv.setText(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}