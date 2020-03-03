package com.chechu.actividad3_m8;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class WidgetDAO extends SQLiteOpenHelper {

    public WidgetDAO(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // intance class thath generate db
        new createDatabase(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //WHAT'S ALL THAT SHIT??
        if (oldVersion == 1 && newVersion == 2){
            db.execSQL ("CREATE TABLE PERFIL (ID_PERFIL INTEGER, ID_USUARIO INTEGER, NOMBRE TEXT);");
        }

        else if (oldVersion == 2 && newVersion == 1){
            db.execSQL ("DROP TABLE IF EXISTS PERFIL;");

        }
    }
}
