package com.sise.agendaescolar.grupo04.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sise.agendaescolar.grupo04.model.Docente;
import com.sise.agendaescolar.grupo04.model.Curso;
import com.sise.agendaescolar.grupo04.model.Apunte;
import com.sise.agendaescolar.grupo04.model.Actividad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";


    private static final String DATABASE_NAME = "agenda.db";
    private static final int DATABASE_VERSION = 1;

    private Context context;


    // 👇 CONSTRUCTOR
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 👇 AQUÍ VA onCreate()
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DOCENTES);
        db.execSQL(CREATE_TABLE_CURSOS);
        db.execSQL(CREATE_TABLE_ACTIVIDAD);
        db.execSQL(CREATE_TABLE_APUNTES);

        Log.d(TAG, "Base de datos agenda creada correctamente");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCENTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURSOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVIDAD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APUNTES);
        onCreate(db);
    }


    private static final String TABLE_DOCENTES = "docentes";

    private static final String COLUMN_DOCENTE_ID = "id";
    private static final String COLUMN_DOCENTE_NOMBRE = "nombre";
    private static final String COLUMN_DOCENTE_CORREO = "correo";
    private static final String COLUMN_DOCENTE_TELEFONO = "telefono";

    //TABLA DE DOCENTES

    private static final String CREATE_TABLE_DOCENTES =
            "CREATE TABLE " + TABLE_DOCENTES + " (" +
                    COLUMN_DOCENTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DOCENTE_NOMBRE + " TEXT NOT NULL, " +
                    COLUMN_DOCENTE_CORREO + " TEXT, " +
                    COLUMN_DOCENTE_TELEFONO + " TEXT" +
                    ")";


    //TABLA CURSOS
    private static final String TABLE_CURSOS = "cursos";

    private static final String COLUMN_CURSO_ID = "id";
    private static final String COLUMN_CURSO_NOMBRE = "nombre";
    private static final String COLUMN_CURSO_DESCRIPCION = "descripcion";
    private static final String COLUMN_CURSO_DOCENTE = "docente";

    private static final String CREATE_TABLE_CURSOS =
            "CREATE TABLE " + TABLE_CURSOS + " (" +
                    COLUMN_CURSO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CURSO_NOMBRE + " TEXT NOT NULL, " +
                    COLUMN_CURSO_DESCRIPCION + " TEXT, " +
                    COLUMN_CURSO_DOCENTE + " TEXT" +
                    ")";



    //TABLA ACTIVIDAD
    private static final String TABLE_ACTIVIDAD = "actividad";

    private static final String COLUMN_ACTIVIDAD_ID = "id";
    private static final String COLUMN_TITULO = "titulo";
    private static final String COLUMN_DESCRIPCION = "descripcion";
    private static final String COLUMN_FECHA = "fecha";
    private static final String COLUMN_TIPO = "tipo";
    private static final String COLUMN_CALIFICACION = "calificacion";
    private static final String COLUMN_ESTADO = "estado";
    private static final String COLUMN_CURSO = "curso";

    private static final String CREATE_TABLE_ACTIVIDAD =
            "CREATE TABLE " + TABLE_ACTIVIDAD + " (" +
                    COLUMN_ACTIVIDAD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITULO + " TEXT NOT NULL, " +
                    COLUMN_DESCRIPCION + " TEXT, " +
                    COLUMN_FECHA + " TEXT, " +
                    COLUMN_TIPO + " TEXT, " +
                    COLUMN_CALIFICACION + " REAL, " +
                    COLUMN_ESTADO + " TEXT, " +
                    COLUMN_CURSO + " TEXT" +
                    ")";





//TABLA APUNTES

    private static final String TABLE_APUNTES = "apuntes";

    private static final String COLUMN_APUNTE_ID = "id";
    private static final String COLUMN_APUNTE_TITULO = "titulo";
    private static final String COLUMN_APUNTE_CONTENIDO = "contenido";
    private static final String COLUMN_APUNTE_FECHA = "fecha";
    private static final String COLUMN_APUNTE_CURSO = "curso";

    private static final String CREATE_TABLE_APUNTES =
            "CREATE TABLE " + TABLE_APUNTES + " (" +
                    COLUMN_APUNTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_APUNTE_TITULO + " TEXT NOT NULL, " +
                    COLUMN_APUNTE_CONTENIDO + " TEXT, " +
                    COLUMN_APUNTE_FECHA + " TEXT, " +
                    COLUMN_APUNTE_CURSO + " TEXT" +
                    ")";




    //METODOS PARA CADA TABLA

    public long addDocente(Docente docente) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_DOCENTE_NOMBRE, docente.getNombre());
        values.put(COLUMN_DOCENTE_CORREO, docente.getCorreo());
        values.put( COLUMN_DOCENTE_TELEFONO, docente.getTelefono());

        long id = db.insert(TABLE_DOCENTES, null, values);
        db.close();
        return id;
    }

    public List<Docente> getAllDocentes() {
        List<Docente> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DOCENTES, null);

        if (cursor.moveToFirst()) {
            do {
                lista.add(new Docente(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }



    //METODO PARA CURSOS

    public long addCurso(Curso curso) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CURSO_NOMBRE, curso.getNombre());
        values.put( COLUMN_CURSO_DESCRIPCION, curso.getDescripcion());
        values.put(COLUMN_CURSO_DOCENTE, curso.getDocente());

        long id = db.insert(TABLE_CURSOS, null, values);
        db.close();
        return id;
    }

    public List<Curso> getAllCursos() {
        List<Curso> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CURSOS, null);

        if (cursor.moveToFirst()) {
            do {
                lista.add(new Curso(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    //METODO UPDATE PARA CURSO

    public int updateCurso(Curso curso) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CURSO_NOMBRE, curso.getNombre());
        values.put(COLUMN_CURSO_DESCRIPCION, curso.getDescripcion());
        values.put(COLUMN_CURSO_DOCENTE, curso.getDocente());

        int rowsAffected = db.update(
                TABLE_CURSOS,
                values,
                COLUMN_CURSO_ID + "=?",
                new String[]{String.valueOf(curso.getId())}
        );

        db.close();
        Log.d(TAG, "Curso actualizado. Filas afectadas: " + rowsAffected);
        return rowsAffected;
    }

    //metodo DELETE CURSO

    public void deleteCurso(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsDeleted = db.delete(
                TABLE_CURSOS,
                COLUMN_CURSO_ID + "=?",
                new String[]{String.valueOf(id)}
        );

        db.close();
        Log.d(TAG, "Curso eliminado. Filas afectadas: " + rowsDeleted);
    }



    //Metodos para Actividad


    public long addActividad(Actividad actividad) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITULO, actividad.getTitulo());
        values.put(COLUMN_DESCRIPCION, actividad.getDescripcion());
        values.put(COLUMN_FECHA, actividad.getFecha());
        values.put(COLUMN_TIPO, actividad.getTipo());
        values.put(COLUMN_CALIFICACION, actividad.getCalificacion());
        values.put(COLUMN_ESTADO, actividad.getEstado());
        values.put(COLUMN_CURSO, actividad.getCurso());

        long id = db.insert(TABLE_ACTIVIDAD, null, values);
        db.close();

        Log.d(TAG, "Actividad agregada con ID: " + id);
        return id;
    }




    public List<Actividad> getAllActividades() {
        List<Actividad> lista = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_ACTIVIDAD +
                        " ORDER BY " + COLUMN_FECHA + " DESC",
                null
        );

        if (cursor.moveToFirst()) {
            do {
                Actividad actividad = new Actividad(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ACTIVIDAD_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITULO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPCION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FECHA)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_CALIFICACION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ESTADO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CURSO))
                );
                lista.add(actividad);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    //metodo UPDATE DE ACTIVIDAD
    public int updateActividad(Actividad actividad) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITULO, actividad.getTitulo());
        values.put(COLUMN_DESCRIPCION, actividad.getDescripcion());
        values.put(COLUMN_FECHA, actividad.getFecha());
        values.put(COLUMN_TIPO, actividad.getTipo());
        values.put(COLUMN_CALIFICACION, actividad.getCalificacion());
        values.put(COLUMN_ESTADO, actividad.getEstado());
        values.put(COLUMN_CURSO, actividad.getCurso());

        int rowsAffected = db.update(
                TABLE_ACTIVIDAD,
                values,
                COLUMN_ACTIVIDAD_ID + "=?",
                new String[]{String.valueOf(actividad.getId())}
        );

        db.close();
        Log.d(TAG, "Actividad actualizada. Filas afectadas: " + rowsAffected);
        return rowsAffected;
    }

    public void deleteActividad(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsDeleted = db.delete(
                TABLE_ACTIVIDAD,
                COLUMN_ACTIVIDAD_ID + "=?",
                new String[]{String.valueOf(id)}
        );

        db.close();
        Log.d(TAG, "Actividad eliminada. Filas afectadas: " + rowsDeleted);
    }




    //METODO PARA APUNTES

    public long addApunte(Apunte apunte) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_APUNTE_TITULO, apunte.getTitulo());
        values.put(COLUMN_APUNTE_CONTENIDO, apunte.getContenido());
        values.put(COLUMN_APUNTE_FECHA, apunte.getFecha());
        values.put(COLUMN_APUNTE_CURSO, apunte.getCurso());

        long id = db.insert(TABLE_APUNTES, null, values);
        db.close();

        Log.d(TAG, "Apunte agregado con ID: " + id);
        return id;
    }


    public List<Apunte> getAllApuntes() {
        List<Apunte> lista = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_APUNTES +
                        " ORDER BY " + COLUMN_APUNTE_FECHA + " DESC",
                null
        );

        if (cursor.moveToFirst()) {
            do {
                Apunte apunte = new Apunte(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_APUNTE_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APUNTE_TITULO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APUNTE_CONTENIDO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APUNTE_FECHA)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APUNTE_CURSO))
                );
                lista.add(apunte);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    //METODO UPDATE PARA APUNTES

    public int updateApunte(Apunte apunte) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_APUNTE_TITULO, apunte.getTitulo());
        values.put(COLUMN_APUNTE_CONTENIDO, apunte.getContenido());
        values.put(COLUMN_APUNTE_FECHA, apunte.getFecha());
        values.put(COLUMN_APUNTE_CURSO, apunte.getCurso());

        int rowsAffected = db.update(
                TABLE_APUNTES,
                values,
                COLUMN_APUNTE_ID + "=?",
                new String[]{String.valueOf(apunte.getId())}
        );

        db.close();
        Log.d(TAG, "Apunte actualizado. Filas afectadas: " + rowsAffected);
        return rowsAffected;
    }


    //DELETE APUNTE

    public void deleteApunte(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowsDeleted = db.delete(
                TABLE_APUNTES,
                COLUMN_APUNTE_ID + "=?",
                new String[]{String.valueOf(id)}
        );

        db.close();
        Log.d(TAG, "Apunte eliminado. Filas afectadas: " + rowsDeleted);
    }

}
