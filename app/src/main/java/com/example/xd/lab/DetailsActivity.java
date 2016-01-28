package com.example.xd.lab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle bundle = getIntent().getExtras();
        Medicine medicine = (Medicine) bundle.get("medicine");
        final TextView nameTextView = (TextView) findViewById(R.id.textName);
        final TextView countryTextView = (TextView) findViewById(R.id.textCountry);
        final TextView shelfLifeTextView = (TextView) findViewById(R.id.editShelfLife);
        final TextView internationalNameTextView = (TextView) findViewById(R.id.textInternationalName);
        final TextView categoryText = (TextView) findViewById(R.id.textCategory);

        nameTextView.setText(medicine.getName());
        countryTextView.setText(medicine.getCountry());
        shelfLifeTextView.setText(DateUtil.format(medicine.getShelfLife()));
        internationalNameTextView.setText(medicine.getInternationalName());
        categoryText.setText(medicine.getCategory());
    }
}
