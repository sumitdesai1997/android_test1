package com.example.android_test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView tvWelcome, tvFees, tvHours, tvTotFees, tvTotHours;
    RadioButton rbUnderGraduate, rbGraduate;
    Spinner spCourse;
    CheckBox cbAccomodation, cbMedicalInsurance;
    Button btnAdd;

    public static String[] courseList = {"Java","Swift","iOS","Android","Database"};
    public static int[] feeList = {1300,1500,1350,1400,1000};
    public static int[] hourList = {6,5,5,7,4};
    public static int hourLimit = 19;
    public static int other= 0;
    public static int fee = 0;
    public static int hour = 0;
    public static int totalFees = 0;
    public static int totalHours = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWelcome = findViewById(R.id.tvWelcome);
        tvFees = findViewById(R.id.tvFees);
        tvHours = findViewById(R.id.tvHours);
        tvTotFees = findViewById(R.id.tvTotFees);
        tvTotHours = findViewById(R.id.tvTotHours);
        rbUnderGraduate = findViewById(R.id.rbUnderGraduate);
        rbGraduate = findViewById(R.id.rbGraduate);
        spCourse = findViewById(R.id.spCourse);
        cbAccomodation = findViewById(R.id.cbAccomodation);
        cbMedicalInsurance = findViewById(R.id.cbMedicalInsurance);
        btnAdd = findViewById(R.id.btnAdd);

        tvWelcome.setText("Welcome "+LoginPage.stdName);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, courseList);
        spCourse.setAdapter(aa);
        spCourse.setOnItemSelectedListener(this);

        rbUnderGraduate.setOnClickListener(new RadioButtonEvents());
        rbGraduate.setOnClickListener(new RadioButtonEvents());

        cbAccomodation.setOnCheckedChangeListener(new CheckBoxEvents());
        cbMedicalInsurance.setOnCheckedChangeListener(new CheckBoxEvents());

        btnAdd.setOnClickListener( new ButtonEvents());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tvFees.setText(String.valueOf(feeList[position]));
        fee = feeList[position];

        tvHours.setText(String.valueOf(hourList[position]));
        hour = hourList[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        tvFees.setText(String.valueOf(feeList[0]));
        fee = feeList[0];

        tvHours.setText(String.valueOf(hourList[0]));
        hour = hourList[0];
    }

    private class RadioButtonEvents implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(rbUnderGraduate.isChecked()){
                hourLimit = 19;
                clear();
            } else {
                hourLimit = 21;
                spCourse.setSelection(0);
                cbAccomodation.setChecked(false);
                cbMedicalInsurance.setChecked(false);
                tvTotFees.setText("0");
                tvTotHours.setText("0");
                totalFees = 0;
                totalHours = 0;
            }
        }
    }

    public void clear(){
        spCourse.setSelection(0);
        cbAccomodation.setChecked(false);
        cbMedicalInsurance.setChecked(false);
        tvTotFees.setText("0");
        tvTotHours.setText("0");
        totalFees = 0;
        totalHours = 0;
    }

    private class CheckBoxEvents implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            other = 0;
            if(cbAccomodation.isChecked()){
                other += 1000;
            }
            if(cbMedicalInsurance.isChecked()){
                other += 700;
            }
        }
    }

    private class ButtonEvents implements  View.OnClickListener{

        @Override
        public void onClick(View v) {
           /*int fee = Integer.parseInt(tvFees.getText().toString());
           int hour = Integer.parseInt(tvHours.getText().toString());*/

            totalFees += other;
            tvTotFees.setText(String.valueOf(totalFees));

            if(totalHours + hour > hourLimit){
               Toast.makeText(getBaseContext(), "You can't add this course", Toast.LENGTH_SHORT).show();
               return;
            }

           totalFees += fee;
           totalHours += hour;

           tvTotFees.setText(String.valueOf(totalFees));
           tvTotHours.setText(String.valueOf(totalHours));
        }
    }



}