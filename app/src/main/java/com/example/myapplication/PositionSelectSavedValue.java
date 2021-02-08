package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PositionSelectSavedValue extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "myApplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_select_saved_value);
    }

    public void OpenDisplaySelectedValue(View view) {
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radio_group);
        Intent intent = new Intent(this, DisplaySelectedValue.class);
        int id = radiogroup.getCheckedRadioButtonId();
        if (id == -1) {
            //no item --> Toast Display
            Toast.makeText(getBaseContext(), "Effettuare una scelta prima di procedere!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            switch (id) {

                case R.id.radio_georotation:
                    String georotation_file_name = "mygeorotationvalue.txt";
                    intent.putExtra(EXTRA_MESSAGE, georotation_file_name);
                    startActivity(intent);
                    break;

                case R.id.radio_magnetic_field:
                    String magnetic_field_file_name = "mymagneticvalue.txt";
                    intent.putExtra(EXTRA_MESSAGE, magnetic_field_file_name);
                    startActivity(intent);
                    break;

                case R.id.radio_orientation:
                    String orientation_file_name = "myorientationvalue.txt";
                    intent.putExtra(EXTRA_MESSAGE, orientation_file_name);
                    startActivity(intent);
                    break;

                case R.id.radio_proximity:
                    String proximity_file_name = "myproximityvalue.txt";
                    intent.putExtra(EXTRA_MESSAGE, proximity_file_name);
                    startActivity(intent);
                    break;
            }

        }
    }
}
