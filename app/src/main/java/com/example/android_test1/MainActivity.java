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

    // creating objects for the designing elements
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

        // assigning value to the objects by finding the view by id
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

        // setting the greeting message for the student
        tvWelcome.setText("Welcome "+LoginPage.stdName);

        // making adapter object and setting that adapter for the spinner to show the name of courses
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, courseList);
        spCourse.setAdapter(aa);

        // on item selected listener event for spinner
        spCourse.setOnItemSelectedListener(this);

        // on click listener event for undergraduated and graduated radio buttons
        rbUnderGraduate.setOnClickListener(new RadioButtonEvents());
        rbGraduate.setOnClickListener(new RadioButtonEvents());

        // on checked change listener event for checkboxes
        cbAccomodation.setOnCheckedChangeListener(new CheckBoxEvents());
        cbMedicalInsurance.setOnCheckedChangeListener(new CheckBoxEvents());

        //  on click listener event for add button
        btnAdd.setOnClickListener( new ButtonEvents());
    }

    // method that will execute on the selection of course from the spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tvFees.setText(String.valueOf(feeList[position]));
        fee = feeList[position];

        tvHours.setText(String.valueOf(hourList[position]));
        hour = hourList[position];
    }

    // method that will execute if no course from the spinner is selected
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        tvFees.setText(String.valueOf(feeList[0]));
        fee = feeList[0];

        tvHours.setText(String.valueOf(hourList[0]));
        hour = hourList[0];
    }

    // class for radio button listener events and implementing onClick event
    private class RadioButtonEvents implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(rbUnderGraduate.isChecked()){
                // changing hour limit to 19 if student is undergraduated
                hourLimit = 19;
                clear();
            } else {
                // changing hour limit to 21 if student is graduated
                hourLimit = 21;
                clear();
            }
        }
    }

    // method to clear data on the select of undergraduated and graduated radio buttons
    public void clear(){
        spCourse.setSelection(0);
        cbAccomodation.setChecked(false);
        cbMedicalInsurance.setChecked(false);
        tvTotFees.setText("0");
        tvTotHours.setText("0");
        totalFees = 0;
        totalHours = 0;
    }

    // class for checkbox event listener and implementing onCheckedChanged method
    private class CheckBoxEvents implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            // subtracting value of other variable from the total fees as now other will have new value as per user choice
            totalFees -= other;
            other = 0;

            if(cbAccomodation.isChecked()){
                other += 1000;
            }
            if(cbMedicalInsurance.isChecked()){
                other += 700;
            }
            // adding the value of other variable to total fees
            totalFees += other;
        }
    }

    // class for button event listener and implementing onClick method
    private class ButtonEvents implements  View.OnClickListener{

        @Override
        public void onClick(View v) {

            // showing updated value of total fees on the click of add button ( because the value for total fees selection is getting updated on selection of checkbox)
            tvTotFees.setText(String.valueOf(totalFees));

            // restricting the user if hour limit is getting exceeded
            if(totalHours + hour > hourLimit){
               Toast.makeText(getBaseContext(), "You can't add this course", Toast.LENGTH_SHORT).show();
               return;
            }

           // adding the current course fee and hour to the total fees and total hours
           totalFees += fee;
           totalHours += hour;

           tvTotFees.setText(String.valueOf(totalFees));
           tvTotHours.setText(String.valueOf(totalHours));
        }
    }



}