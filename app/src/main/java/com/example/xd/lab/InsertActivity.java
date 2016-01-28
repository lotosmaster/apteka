package com.example.xd.lab;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        final TextView nameTextView = (TextView) findViewById(R.id.editName);
        final TextView countryTextView = (TextView) findViewById(R.id.editCountry);
        final EditText shelfLifeTextView = (EditText) findViewById(R.id.editShelfLife);
        shelfLifeTextView.setInputType(InputType.TYPE_NULL);
        shelfLifeTextView.requestFocus();
        final TextView internationalNameTextView = (TextView) findViewById(R.id.editInternationalName);
        final Spinner categorySpinner = (Spinner) findViewById(R.id.editCategoryName);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        final Calendar newCalendar = Calendar.getInstance();
        final DatePickerDialog datePickerDialog = new DatePickerDialog(InsertActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                shelfLifeTextView.setText(DateUtil.SIMPLE_DATE_FORMAT.format(newDate.getTime()));
                shelfLifeTextView.setHint(String.valueOf(newDate.getTimeInMillis()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        shelfLifeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbhelper = new DbHelper(InsertActivity.this);

                Medicine medicine = new Medicine();
                medicine.setName(nameTextView.getText().toString());
                medicine.setShelLife(shelfLifeTextView.getHint().toString());
                medicine.setCountry(countryTextView.getText().toString());
                medicine.setInternationalName(internationalNameTextView.getText().toString());
                medicine.setCategory(categorySpinner.getSelectedItem().toString());
                dbhelper.addMedicine(medicine);
                startActivity(new Intent(InsertActivity.this, HomeActivity.class));
            }
        });
    }
}
