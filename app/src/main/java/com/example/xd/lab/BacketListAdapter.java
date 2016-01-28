package com.example.xd.lab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 27.01.2016.
 */
public class BacketListAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<Medicine> objects;

    BacketListAdapter(Context context, List<Medicine> products) {
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
        return getMedicine(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.adapter_home, parent, false);
        }

        Medicine p = getMedicine(position);
        ((TextView) view.findViewById(R.id.medicine_name)).setText(p.getName());
        ((TextView) view.findViewById(R.id.medicine_status)).setText(p.getStatus() == "0" ? "Новый" : "Просрочено");
        String shelfLife = p.getShelfLife();
        ((TextView) view.findViewById(R.id.medicine_shelf_time)).setText(DateUtil.format(shelfLife));
        return view;
    }

    private Medicine getMedicine(int position) {
        return ((Medicine) getItem(position));
    }
}
