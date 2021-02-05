package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class GravitySensors extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "myApplication.MESSAGE";

    SensorManager sensormanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity_sensors);
        final LinearLayout lm = (LinearLayout) findViewById(R.id.LinearMainGravity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        sensormanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensormanager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null) {
            List<Sensor> GravityList = sensormanager.getSensorList(Sensor.TYPE_GRAVITY);
            View.OnClickListener[] listeners = new View.OnClickListener[GravityList.size()];
            final Context context = this;
            for (int q = 0; q < GravityList.size(); q++) {
                final int temp = q;
                listeners[q] = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, GravityFeatures.class);
                        intent.putExtra(EXTRA_MESSAGE, String.valueOf(temp));
                        startActivity(intent);
                    }
                };
            }
            for (int i = 0; i < GravityList.size(); i++) {
                LinearLayout ll = new LinearLayout(this);
                final Button btn = new Button(this);
                btn.setId(i);
                btn.setText("Sensore Gravità numero: " + String.valueOf(i + 1));
                btn.setLayoutParams(params);
                btn.setOnClickListener(listeners[i]);
                ll.addView(btn);
                lm.addView(ll);
            }
        } else {
            LinearLayout ll = new LinearLayout(this);
            final TextView textview = new TextView(this);
            textview.setText("Sorry, there are no gravity sensors on your device");
            textview.setLayoutParams(params);
            ll.addView(textview);
            lm.addView(ll);
        }
    }
}
