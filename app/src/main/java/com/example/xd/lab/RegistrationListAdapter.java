package com.example.xd.lab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 26.01.2016.
 */
public class RegistrationListAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<Medicine> objects;

    RegistrationListAdapter(Context context, List<Medicine> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.adapter_registration, parent, false);
        }

        final Medicine medicine = getMedicineByPosition(position);

        TextView medicineName = (TextView) view.findViewById(R.id.medicine_name);
        medicineName.setText(medicine.getName());
        medicineName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("medicine", medicine);
                Intent intent = new Intent(ctx, DetailsActivity.class);
                intent.putExtras(bundle);
                ctx.startActivity(intent);
            }
        });
        view.findViewById(R.id.buttonEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("medicine", medicine);
                Intent intent = new Intent(ctx, UpdateActivity.class);
                intent.putExtras(bundle);
                ctx.startActivity(intent);
            }
        });


        return view;
    }

    // товар по позиции
    Medicine getMedicineByPosition(int position) {
        return ((Medicine) getItem(position));
    }



}
