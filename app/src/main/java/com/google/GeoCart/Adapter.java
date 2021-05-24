package com.google.GeoCart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Alimento> alimentos;

    public Adapter(Context context, int layout, ArrayList<Alimento> alimentos) {
        this.context = context;
        this.layout = layout;
        this.alimentos = alimentos;
    }

    @Override
    public int getCount() {
        return this.alimentos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.alimentos.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        // Copiamos la vista
        View v = convertView;

        //Inflamos la vista con nuestro propio layout
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);

        v = layoutInflater.inflate(R.layout.list_item, null);
        // Valor actual según la posición

        String currentName = alimentos.get(position).nombrep;
        int unidades = alimentos.get(position).unidades;

        // Referenciamos el elemento a modificar y lo rellenamos
        TextView textView = (TextView) v.findViewById(R.id.textView);
        TextView textView2 = (TextView) v.findViewById(R.id.textView2);

        textView.setText(currentName);
        textView2.setText("Uds : " + unidades);
        //Devolvemos la vista inflada
        return v;
    }

    public int getId(int position) {
       int id = alimentos.get(position).id;
        return id;
    }
}