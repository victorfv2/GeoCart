package com.google.GeoCart;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context, String db_name) {
        super(context, db_name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Cursor getAllData(String tableName) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + tableName, null);

        return res;

    }

    public ArrayList<Tiendas> consultaElemento(String elemento) {

        ArrayList<Tiendas> lista = new ArrayList<>();
        Tiendas auxTiendas;

        SQLiteDatabase sqldb = getReadableDatabase();
        String sentencia = "SELECT * FROM tiendas where CodPostal  = '" + elemento + "'";
        Cursor cursor = sqldb.rawQuery(sentencia, null);

        if (cursor.moveToFirst()) {  // Se puede sustituir por cursor!=null
            do {
                auxTiendas = new Tiendas();
                auxTiendas.setLatitud(cursor.getDouble(1));
                auxTiendas.setLongitud(cursor.getDouble(2));
                auxTiendas.setNombre(cursor.getString(3));
                lista.add(auxTiendas);

            } while (cursor.moveToNext());

        }
        return lista;
    }

    public ArrayList<Alimento> consultaTotal() {
        ArrayList<Alimento> lista = new ArrayList<>();
        Alimento auxAlimento;

        SQLiteDatabase sqldb = getReadableDatabase();
        String sentencia = "SELECT * FROM Alimento";
        Cursor cursor = sqldb.rawQuery(sentencia, null);

        if (cursor.moveToFirst()) {  // Se puede sustituir por cursor!=null
            do {
                auxAlimento = new Alimento();
                auxAlimento.setId(cursor.getInt(0));
                auxAlimento.setNombrep(cursor.getString(1));
                auxAlimento.setBarCode(cursor.getString(2));
                auxAlimento.setUnidades(cursor.getInt(3));
                lista.add(auxAlimento);

            } while (cursor.moveToNext());
        }

        return lista;
    }

    public ArrayList<Alimento> LiCompra() {
        ArrayList<Alimento> lista = new ArrayList<>();
        Alimento auxAlimento;

        SQLiteDatabase sqldb = getReadableDatabase();
        String sentencia = "SELECT * FROM Alimento  WHERE unidades <=3";
        Cursor cursor = sqldb.rawQuery(sentencia, null);

        if (cursor.moveToFirst()) {  // Se puede sustituir por cursor!=null
            do {
                auxAlimento = new Alimento();
                auxAlimento.setId(cursor.getInt(0));
                auxAlimento.setNombrep(cursor.getString(1));
                auxAlimento.setBarCode(cursor.getString(2));
                auxAlimento.setUnidades(cursor.getInt(3));
                lista.add(auxAlimento);

            } while (cursor.moveToNext());
        }

        return lista;
    }

    public ArrayList<Lotes> consultaLotes(int idprod) {

        ArrayList<Lotes> lista = new ArrayList<>();
        Lotes auxLotes;

        SQLiteDatabase sqldb = getReadableDatabase();
        String sentencia = "SELECT * FROM Lotes where ID_Producto  = " + idprod + " AND fecha_caducidad >= DATE('now')";
        Cursor cursor = sqldb.rawQuery(sentencia, null);

        if (cursor.moveToFirst()) {  // Se puede sustituir por cursor!=null
            do {
                auxLotes = new Lotes();
                auxLotes.setIDL(cursor.getInt(0));
                auxLotes.setFecha_adq((cursor.getString(1)));
                auxLotes.setFecha_cad((cursor.getString(2)));
                auxLotes.setUnidades(cursor.getInt(3));
                lista.add(auxLotes);

            } while (cursor.moveToNext());

        }
        return lista;

    }

    public ArrayList<Alimento> comBarcode(String codibar) {
        ArrayList<Alimento> lista = new ArrayList<>();
        Alimento auxAlimento;

        SQLiteDatabase sqldb = getReadableDatabase();
        String sentencia = "SELECT * FROM Alimento  WHERE BarCode = '"+codibar+"'";
        Cursor cursor = sqldb.rawQuery(sentencia, null);

        if (cursor.moveToFirst()) {  // Se puede sustituir por cursor!=null
            do {
                auxAlimento = new Alimento();
                auxAlimento.setId(cursor.getInt(0));
                auxAlimento.setNombrep(cursor.getString(1));
                auxAlimento.setBarCode(cursor.getString(2));
                auxAlimento.setUnidades(cursor.getInt(3));
                lista.add(auxAlimento);

            } while (cursor.moveToNext());
        }

        return lista;
    }

    public int  getIdc(String barcode ) {
        Alimento auxAlimento;
        int id=0;
        SQLiteDatabase sqldb = getReadableDatabase();
        String sentencia = "SELECT ID FROM Alimento WHERE BarCode='"+barcode+"'";
        Cursor cursor = sqldb.rawQuery(sentencia, null);

        if (cursor.moveToFirst()) {  // Se puede sustituir por cursor!=null

                auxAlimento = new Alimento();
                auxAlimento.setId(cursor.getInt(0));
                 id =auxAlimento.getId();
                 return id;
        }
        return id;
    }

    public void  insAli(String nombrep,String codbar ) {
        int id=0;
        SQLiteDatabase sqldb = getReadableDatabase();
        String sentencia = "INSERT INTO Alimento (Nombre,BarCode)VALUES ('"+nombrep+"','"+codbar+"')";
        Cursor cursor = sqldb.rawQuery(sentencia, null);
    }
    public void  insLote(String fecha_cad,int uds,int id ) {
        SQLiteDatabase sqldb = getReadableDatabase();
        String sentencia = "INSERT INTO Lotes ( fecha_caducidad,Unidades,ID_Producto)VALUES ('"+fecha_cad+"',"+uds+","+id+")";
        sqldb.execSQL(sentencia);
    }

    public void  updateLote(int uds,int id ) {
        SQLiteDatabase sqldb = getReadableDatabase();
        String sentencia = "UPDATE Lotes SET   Unidades ="+uds+" WHERE ID_Lote ="+id;
        sqldb.execSQL(sentencia);
    }

}
