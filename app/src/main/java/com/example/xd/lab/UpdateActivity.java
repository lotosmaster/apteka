package com.example.xd.lab;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Bundle bundle = getIntent().getExtras();
        Medicine medicine = (Medicine) bundle.get("medicine");
        id = medicine.getID();
        final TextView nameTextView = (TextView) findViewById(R.id.editName);
        final TextView countryTextView = (TextView) findViewById(R.id.editCountry);
        final TextView shelfLifeTextView = (TextView) findViewById(R.id.editShelfLife);
        shelfLifeTextView.setInputType(InputType.TYPE_NULL);
        shelfLifeTextView.requestFocus();
        final TextView internationalNameTextView = (TextView) findViewById(R.id.editInternationalName);
        final Spinner categorySpinner = (Spinner) findViewById(R.id.editCategoryName);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        nameTextView.setText(medicine.getName());
        countryTextView.setText(medicine.getCountry());
        shelfLifeTextView.setText(DateUtil.format(medicine.getShelfLife()));
        internationalNameTextView.setText(medicine.getInternationalName());
        categorySpinner.setSelection(adapter.getPosition(medicine.getCategory()));
        final Calendar newCalendar = Calendar.getInstance();
        final DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        findViewById(R.id.buttonUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbhelper = new DbHelper(UpdateActivity.this);

                Medicine medicine = new Medicine();
                medicine.setID(id);
                medicine.setName(nameTextView.getText().toString());
                medicine.setShelLife(shelfLifeTextView.getHint().toString());
                medicine.setCountry(countryTextView.getText().toString());
                medicine.setInternationalName(internationalNameTextView.getText().toString());
                medicine.setCategory(categorySpinner.getSelectedItem().toString());
                dbhelper.updateMedicine(medicine);
                startActivity(new Intent(UpdateActivity.this, HomeActivity.class));
            }
        });
    }
}
