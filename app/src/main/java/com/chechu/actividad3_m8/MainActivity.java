package com.chechu.actividad3_m8;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        Log.i("[INFO]", "Updating the Widget with updateAppWidget!");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widgetchechu);
        SharedPreferences prefs = context.getSharedPreferences("WidgetPrefs", Context.MODE_PRIVATE);
        String grupoName = prefs.getString("fw_" + appWidgetId + "_grupo", null);
        String className = prefs.getString("fw_" + appWidgetId + "_class", null);
        String profName = prefs.getString("fw_" + appWidgetId + "_prof", null);
        views.setTextViewText(R.id.timeText, getTime());
        views.setTextViewText(R.id.grupoName, "Grupo: " + grupoName);
        views.setTextViewText(R.id.claseName, "Clase: " + className);
        views.setTextViewText(R.id.profName, "Prof: " + profName);
        appWidgetManager.updateAppWidget(appWidgetId, views);


    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    //This is called when an instance the App Widget is created for the first time.
    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        Log.i("[INFO]", "ENABLED");
    }

    //This is called when the last instance of your App Widget is deleted from the App Widget host.
    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    //This is called for every broadcast and before each of the above callback methods.
    @Override
    public void onReceive(Context context, Intent intent) {
    }

    //Gets the time
    public static String getTime() {
        //return DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date());

        Date d=new Date(new Date().getTime());
        String s=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(d);
        return s;
    }

}
