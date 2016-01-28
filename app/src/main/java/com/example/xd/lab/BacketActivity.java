package com.example.xd.lab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class BacketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backet);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DbHelper dbhelper = new DbHelper(this);
        List newMedicines = dbhelper.getMedicineNew();
        List expired = dbhelper.getExpiredMedicines();
        newMedicines.addAll(expired);
        ListView homeList = (ListView) findViewById(R.id.home_list);
        HomeListAdapter adapter = new HomeListAdapter(this, newMedicines);
        homeList.setAdapter(adapter);
    }
}
