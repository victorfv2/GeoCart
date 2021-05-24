package com.google.GeoCart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaCompra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compra);
        ListView lv = findViewById(R.id.listaco);
        DataBaseHelper sql = new DataBaseHelper(this, MainActivity.DB_NAME);
        ArrayList<Alimento> alimentos = (ArrayList<Alimento>) sql.LiCompra();
        AdapterCompra myAdapter = new AdapterCompra(this, R.layout.list_coitem, alimentos);
        lv.setAdapter(myAdapter);
    }
}