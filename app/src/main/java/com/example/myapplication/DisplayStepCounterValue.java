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

public class DisplayStepCounterValue extends AppCompatActivity implements SensorEventListener  {

    TextView displayvalue;
    TextView tv;

    private SensorManager sensormanager;
    private Sensor StepCountersensor;

    static final int read_block_size = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_step_counter_value);
        displayvalue = findViewById(R.id.displaystepcountervalue);
        Intent intent = getIntent();
        int step_counter_sensor_index = Integer.parseInt(intent.getStringExtra(StepCounterSensors.EXTRA_MESSAGE));
        sensormanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> StepCounterList = sensormanager.getSensorList(Sensor.TYPE_STEP_COUNTER);
        StepCountersensor = StepCounterList.get(step_counter_sensor_index);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float step_counter_value = event.values[0];
        displayvalue.setText("Sensor value : " + step_counter_value + "\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensormanager.registerListener(this, StepCountersensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensormanager.unregisterListener(this);
    }

    public void BtnSafe(View view) {
        //Write text into file
        try {
            FileOutputStream fileout = openFileOutput("mystepcountervalue.txt", MODE_PRIVATE);
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
            FileInputStream filein = openFileInput("mystepcountervalue.txt");
            InputStreamReader inputread = new InputStreamReader(filein);

            char [] inputBuffer = new char[read_block_size];
            String s = "";
            int charRead;

            while((charRead = inputread.read(inputBuffer)) > 0) {
                //Char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            tv = findViewById(R.id.stepcountersavedvalue);
            inputread.close();
            tv.setText(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}