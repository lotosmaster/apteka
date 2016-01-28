package com.example.xd.lab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private DbHelper dbhelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbhelper = new DbHelper(this);

        findViewById(R.id.buttonRegistrationMedicine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, RegistrationActivity.class));
            }
        });
        findViewById(R.id.buttonBasket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, BacketActivity.class));
            }
        });
        findViewById(R.id.buttonSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshHomeList();
    }

    private void refreshHomeList() {
        ((TextView) findViewById(R.id.textCount)).setText(String.valueOf(dbhelper.getMedicineCount()));
        ListView homeList = (ListView) findViewById(R.id.home_list);
        SharedPreferences sharedPreferences = getSharedPreferences("settings", 0);
        int expiredType = sharedPreferences.getInt("expired", 0);
        HomeListAdapter adapter = new HomeListAdapter(this, dbhelper.getMedicinesByComingEnd(DateUtil.getTimeMillis(expiredType)));
        homeList.setAdapter(adapter);
        homeList.setOnItemLongClickListener(HomeActivity.this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        dbhelper.deleteMedicine(id);
        refreshHomeList();
        return false;
    }
}
