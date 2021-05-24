package com.google.GeoCart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    DataBaseHelper sql;
    ArrayList<Lotes> listaLotes;
    TableLayout tlTabla;

    TableRow fila;

    TextView tvId,tvfechAdq, tvfechaCad, tvUds;
    EditText editdate;
    NumberPicker np;
    Button btnIns,btnMod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        sql = new DataBaseHelper(getApplicationContext(),MainActivity.DB_NAME);
        tlTabla = findViewById(R.id.tlTabla);
        TextView tv = findViewById(R.id.textView3);
        btnIns = findViewById(R.id.ins);
        btnMod= findViewById(R.id.btnMod);
        editdate= findViewById(R.id.editTextDate);
        np=findViewById(R.id.editTextNumberDecimal);
        np.setMinValue(0);
        np.setMaxValue(30);
        Bundle bundle = getIntent().getExtras();
        int idl=bundle.getInt("id");
        String nomp=bundle.getString("nomp");
        tv.setText(nomp);
        listaLotes = (ArrayList<Lotes>) sql.consultaLotes(idl);
        String[] arraySpinner= new String[listaLotes.size()];

        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutID = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutfechadq = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutfechCad = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutUds = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

        // TABLA
        for (int i = -1; i <= listaLotes.size()-1; i++) {
            fila = new TableRow(this);
            fila.setLayoutParams(layoutFila);

            if (i == -1) {

                tvId = new TextView(this);
                tvId.setText("ID Lote");
                tvId.setGravity(Gravity.CENTER);
                tvId.setPadding(10, 10, 10, 10);
                tvId.setLayoutParams(layoutID);
                fila.addView(tvId);

                tvfechAdq = new TextView(this);
                tvfechAdq.setText("Fecha_Adquisicion");
                tvfechAdq.setGravity(Gravity.CENTER);
                tvfechAdq.setPadding(10, 10, 10, 10);
                tvfechAdq.setLayoutParams(layoutfechadq);
                fila.addView(tvfechAdq);

                tvfechaCad = new TextView(this);
                tvfechaCad.setText("Fecha_caducidad");
                tvfechaCad.setGravity(Gravity.CENTER);
                tvfechaCad.setPadding(10, 10, 50, 10);
                tvfechaCad.setLayoutParams(layoutfechadq);
                fila.addView(tvfechaCad);

                tvUds = new TextView(this);
                tvUds.setText("Uds.");
                tvUds.setGravity(Gravity.CENTER);
                tvUds.setPadding(10, 10, 10, 10);
                tvUds.setLayoutParams(layoutfechadq);
                fila.addView(tvUds);

                tlTabla.addView(fila);
            } else {

                tvId = new TextView(this);
                tvId.setText(String.valueOf(listaLotes.get(i).getIDL()));
                arraySpinner[i]=(String.valueOf(listaLotes.get(i).getIDL()));
                tvId.setGravity(Gravity.CENTER);
                tvId.setPadding(10, 10, 10, 10);
                tvId.setLayoutParams(layoutID);
                fila.addView(tvId);

                tvfechAdq = new TextView(this);
                tvfechAdq.setText(listaLotes.get(i).getFecha_adq());
                tvfechAdq.setPadding(120, 10, 10, 10);
                tvfechAdq.setLayoutParams(layoutfechadq);
                fila.addView(tvfechAdq);

                tvfechaCad = new TextView(this);
                tvfechaCad.setGravity(Gravity.CENTER);
                tvfechaCad.setText(listaLotes.get(i).getFecha_cad());
                tvfechaCad.setPadding(10, 10, 40, 10);
                tvfechaCad.setLayoutParams(layoutfechCad);
                fila.addView(tvfechaCad);

                tvUds = new TextView(this);
                tvUds.setText(String.valueOf(listaLotes.get(i).getUnidades()));
                tvUds.setPadding(40, 10, 10, 10);
                tvUds.setLayoutParams(layoutUds);
                fila.addView(tvUds);

                tlTabla.addView(fila);
            }
        }
        btnIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sql.insLote(String.valueOf(editdate.getText()),np.getValue(),idl);
                Context context = getApplicationContext();
                CharSequence text = "Se ha insertado correctamente";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoPersonalizado(arraySpinner);
            }
        });

    }
    private void mostrarDialogoPersonalizado(String[] arraySpinner) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity2.this);

        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_personalizado_modificar, null);

        builder.setView(view);


        final AlertDialog dialog = builder.create();
        dialog.show();

        Spinner sp = view.findViewById(R.id.spinner);
        NumberPicker npd = view.findViewById(R.id.npd);
        npd.setMinValue(0);
        npd.setMaxValue(30);
        Button btnInsertar = view.findViewById(R.id.btndiaMod);
        Button btnCancelar = view.findViewById(R.id.btnCancelar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);


        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sql.updateLote(npd.getValue(),Integer.parseInt(sp.getSelectedItem().toString()));
                dialog.dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}