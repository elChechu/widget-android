package com.chechu.actividad3_m8;

import android.database.sqlite.SQLiteDatabase;

public class createDatabase {

    public final String alumnos = "CREATE TABLE alumnos (ID INTEGER, nombre TEXT, grupo TEXT);";
    public final String dam = "CREATE TABLE dam2 (codigo TEXT, className TEXT, profe TEXT, dia TEXT, hora TEXT);";

    createDatabase(SQLiteDatabase database){
        database.execSQL(alumnos);
        database.execSQL(dam);
        database.execSQL("INSERT INTO dam2 VALUES(\"DUAL\", \"DUAL\", \"Andres Prieto\", \"Lunes\", \"15:00\");");
        database.execSQL("INSERT INTO dam2 VALUES(\"M5-M8\", \"Android\", \"Lluis Perpinyà\", \"Lunes\", \"17:00\");");
        database.execSQL("INSERT INTO dam2 VALUES(\"M10\", \"M10\", \"Andres Prieto\", \"Lunes\", \"18:20\");");
        database.execSQL("INSERT INTO dam2 VALUES(\"M10\", \"M10\", \"Andres Prieto\", \"Lunes\", \"19:20\");");
    }
}
