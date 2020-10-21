package com.example.listview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.listview.R;
import com.example.listview.mode.Nhac;
import com.example.listview.mode.Nhac;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Nhac> {
    private Context context;
    private int resource;
    private ArrayList<Nhac> arrList;


    public CustomAdapter(@NonNull Context context, int resource, ArrayList<Nhac> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.arrList=objects;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.listview,parent,false);
        TextView tv_color = (TextView) convertView.findViewById(R.id.tv_color);
        TextView tv_nhac = (TextView) convertView.findViewById(R.id.tv_tennhac);
        TextView tv_nghesi = (TextView) convertView.findViewById(R.id.tv_nghesi);
        Nhac nhac = arrList.get(position);
        tv_color.setBackgroundColor(nhac.getmColor());
        tv_color.setText((position+1)+"");
        tv_nhac.setText(nhac.getmNhac());
        tv_nghesi.setText(nhac.getmTen());
        return convertView;
    }
    public  void  removeItem(ArrayList<Nhac> items)
    {
        for (Nhac item : items)
        {
            arrList.remove(item);
        }
        notifyDataSetChanged();
    }
}
