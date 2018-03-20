package com.sulus.encuestasapp.Utilerias;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sulus.encuestasapp.Clases.Encuesta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by betaripv on 17/03/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Nombre de la Base de Datos
    private static final String DATABASE_NAME = "encuestasManager";

    // Nombre de la tabla
    private static final String TABLE_ENCUESTAS = "encuestas";

    // Contacts Table Columns names
    private static final String KEY_ID = "usuario";
    private static final String KEY_SITUACION = "situacion";
    private static final String KEY_FUE_ENTREVIS = "fue_entrevistado";
    private static final String KEY_RESP1 = "resp1";
    private static final String KEY_RESP2 = "resp2";
    private static final String KEY_RESP3 = "resp3";
    private static final String KEY_RESP4 = "resp4";
    private static final String KEY_RESP5 = "resp5";
    private static final String KEY_LAT = "latitud";
    private static final String KEY_LONG = "longitud";
    private static final String KEY_MUNICIPIO = "municipio";
    private static final String KEY_SECCION = "seccion";
    private static final String KEY_TELEFONO = "telefono";
    private static final String KEY_CORREO = "correo";
    private static final String KEY_HORA_ENTRE = "hora_entrevista";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_APP1 = "app1";
    private static final String KEY_APP2 = "app2";

    String[] columnas = new String[]{KEY_ID, KEY_NOMBRE, KEY_APP1, KEY_APP2, KEY_MUNICIPIO, KEY_SECCION,
            KEY_SITUACION, KEY_FUE_ENTREVIS, KEY_CORREO, KEY_TELEFONO, KEY_RESP1, KEY_RESP2, KEY_RESP3,
            KEY_RESP4, KEY_RESP5, KEY_LONG, KEY_LAT, KEY_HORA_ENTRE
    };

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_ENCUESTAS + "("
                + KEY_ID + " TEXT ," + KEY_NOMBRE + " TEXT,"
                + KEY_APP1 + " TEXT ," + KEY_APP2 + " TEXT, "
                + KEY_MUNICIPIO + " TEXT ," + KEY_SECCION + " TEXT,"
                + KEY_SITUACION + " TEXT ," + KEY_FUE_ENTREVIS + " TEXT, "
                + KEY_CORREO + " TEXT ," + KEY_TELEFONO + " TEXT,"
                + KEY_RESP1 + " TEXT ," + KEY_RESP2 + " TEXT, "
                + KEY_RESP3 + " TEXT ," + KEY_RESP4 + " TEXT,"
                + KEY_RESP5 + " TEXT ," + KEY_LONG + " TEXT, "
                + KEY_LAT + " TEXT ,"
                + KEY_HORA_ENTRE + "  DATETIME DEFAULT CURRENT_TIMESTAMP" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENCUESTAS);

        // Create tables again
        onCreate(db);
    }

    public void elimnarBase() {

    }

    // Adding new contact
    public void addEncuesta(Encuesta nueva) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(KEY_ID, nueva.getIdentificador());
        values.put(KEY_NOMBRE, nueva.getNombre());
        values.put(KEY_APP1, nueva.getApp1());
        values.put(KEY_APP2, nueva.getApp2());
        values.put(KEY_MUNICIPIO, nueva.getMunicipio());
        values.put(KEY_SECCION, nueva.getSeccion());
        values.put(KEY_TELEFONO, nueva.getTelfono());
        values.put(KEY_CORREO, nueva.getCorreo());
        values.put(KEY_LAT, nueva.getLongi());
        values.put(KEY_LONG, nueva.getIdentificador());
        values.put(KEY_RESP1, nueva.getIdentificador());
        values.put(KEY_RESP2, nueva.getIdentificador());
        values.put(KEY_RESP3, nueva.getIdentificador());
        values.put(KEY_RESP4, nueva.getIdentificador());
        values.put(KEY_RESP5, nueva.getIdentificador());
        values.put(KEY_FUE_ENTREVIS, nueva.getIdentificador());
        values.put(KEY_SITUACION, nueva.getIdentificador());


        // Inserting Row
        db.insert(TABLE_ENCUESTAS, null, values);
        db.close(); // Closing database connection
    }

    Encuesta getEncuesta(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ENCUESTAS, columnas, KEY_ID + "=?", new String[]{id}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Encuesta enc = new Encuesta();
        enc.setIdentificador(cursor.getString(0));
        enc.setNombre(cursor.getString(1));
        enc.setApp1(cursor.getString(2));
        enc.setApp2(cursor.getString(3));
        enc.setMunicipio(cursor.getString(4));
        enc.setSeccion(cursor.getString(5));
        enc.setSituacion(cursor.getString(6));
        enc.setContestada(cursor.getInt(7));
        enc.setCorreo(cursor.getString(8));
        enc.setTelfono(cursor.getString(9));

        String[] respuestas = new String[5];
        respuestas[0] = cursor.getString(10);
        respuestas[1] = cursor.getString(11);
        respuestas[2] = cursor.getString(12);
        respuestas[3] = cursor.getString(13);
        respuestas[4] = cursor.getString(14);

        enc.setRespuestas(respuestas);

        enc.setLongi(cursor.getDouble(15));
        enc.setLat(cursor.getDouble(16));
        enc.setFechaCaptura(cursor.getString(17));

        return enc;

    }


    public void deleteEncuestas(Encuesta encuesta) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ENCUESTAS, KEY_ID + " = ?",
                new String[]{String.valueOf(encuesta.getIdentificador())});
        db.close();
    }


    public int getEncuestasCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ENCUESTAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    // Getting All Contacts
    public List<Encuesta> getAllContacts() {
        List<Encuesta> encuestaList = new ArrayList<Encuesta>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ENCUESTAS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Encuesta enc = new Encuesta();
                enc.setIdentificador(cursor.getString(0));
                enc.setNombre(cursor.getString(1));
                enc.setApp1(cursor.getString(2));
                enc.setApp2(cursor.getString(3));
                enc.setMunicipio(cursor.getString(4));
                enc.setSeccion(cursor.getString(5));
                enc.setSituacion(cursor.getString(6));
                enc.setContestada(cursor.getInt(7));
                enc.setCorreo(cursor.getString(8));
                enc.setTelfono(cursor.getString(9));

                String[] respuestas = new String[5];
                respuestas[0] = cursor.getString(10);
                respuestas[1] = cursor.getString(11);
                respuestas[2] = cursor.getString(12);
                respuestas[3] = cursor.getString(13);
                respuestas[4] = cursor.getString(14);

                enc.setRespuestas(respuestas);

                enc.setLongi(cursor.getDouble(15));
                enc.setLat(cursor.getDouble(16));
                enc.setFechaCaptura(cursor.getString(17));

                // Adding contact to list
                encuestaList.add(enc);
            } while (cursor.moveToNext());
        }

        // return contact list
        return encuestaList;
    }


}
