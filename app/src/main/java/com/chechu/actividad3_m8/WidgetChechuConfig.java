package com.chechu.actividad3_m8;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class WidgetChechuConfig extends AppCompatActivity {

    private int widgetId = 0;
    private EditText nameInput;
    private EditText grupoInput;
    private Button loginBtn;
    private Button exitBtn;
    private WidgetDAO sqlDAO;
    private SQLiteDatabase database;

    private String className = "Sin clase";
    private String profName = "Sin profe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqlDAO = new WidgetDAO(this, "DBAlumni", null, 1);
        database = sqlDAO.getWritableDatabase();

        String time = MainActivity.getTime().split(" ")[1].split(":")[0];
        System.out.println("SHOWUMEWHATUGOT: " + time);

        @SuppressLint("Recycle") Cursor c = database.rawQuery("SELECT * FROM dam2 WHERE dia = 'Lunes' AND hora LIKE '"+time+"%'", null);
        c.moveToFirst();

        className = c.getString(1);
        profName = c.getString(2);

        Intent intentOren = getIntent();
        Bundle params = intentOren.getExtras();

        assert params != null;
        widgetId = params.getInt(

            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID);
            setResult(RESULT_CANCELED);
            nameInput = findViewById(R.id.nameInput);
            grupoInput = findViewById(R.id.grupoInput);
            loginBtn = findViewById(R.id.loginBtn);

            exitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            SharedPreferences prefs = getSharedPreferences("WidgetPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            //Change start
            editor.putString("fw_" + widgetId + "_name", nameInput.getText().toString());
            editor.putString("fw_" + widgetId + "_grupo", grupoInput.getText().toString());
            editor.putString("fw_" + widgetId + "_class", className);
            editor.putString("fw_" + widgetId + "_prof", profName);
            editor.apply();

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(WidgetChechuConfig.this);
            System.out.println("[INFO] Updating the widget");
            MainActivity.updateAppWidget(WidgetChechuConfig.this, appWidgetManager, widgetId);

            Intent resultado = new Intent();
            resultado.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);

            resultado.putExtra("Name", nameInput.getText().toString());
            setResult(RESULT_OK, resultado);
            finish();
            }
        });
    }
}
