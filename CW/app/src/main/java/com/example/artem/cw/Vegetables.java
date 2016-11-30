package com.example.artem.cw;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by Artem on 18.09.2016.
 */
public class Vegetables extends Activity {

    public static final String APP_PREFERENCES = "mysettings";


    ArrayList<ToggleButton> listTB = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vegetables);

        listTB.add((ToggleButton) findViewById(R.id.ve_13));
        listTB.add((ToggleButton) findViewById(R.id.ve_14));
        listTB.add((ToggleButton) findViewById(R.id.ve_15));
        listTB.add((ToggleButton) findViewById(R.id.ve_16));
        listTB.add((ToggleButton) findViewById(R.id.ve_17));
        listTB.add((ToggleButton) findViewById(R.id.ve_18));
        listTB.add((ToggleButton) findViewById(R.id.ve_19));
        listTB.add((ToggleButton) findViewById(R.id.ve_20));
        listTB.add((ToggleButton) findViewById(R.id.ve_21));
        listTB.add((ToggleButton) findViewById(R.id.ve_22));
        listTB.add((ToggleButton) findViewById(R.id.ve_23));
        listTB.add((ToggleButton) findViewById(R.id.ve_24));
        listTB.add((ToggleButton) findViewById(R.id.ve_25));
        listTB.add((ToggleButton) findViewById(R.id.ve_26));
        listTB.add((ToggleButton) findViewById(R.id.ve_27));

        loadSelection();
    }

    public void onToggleButtonClick (View button){
        getApplicationContext();
        //aaa();
        /*Toast.makeText(
                getApplicationContext(),
                Boolean.toString(((ToggleButton) button).isChecked()),
                Toast.LENGTH_SHORT).show();*/
    }


    // SharedPreferences sPref;



    String f_1 = "f_1";
    String f_2 = "f_2";
    String f_3 = "f_3";
    void saveSelection()
    {

        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE); //получаем объект sPref класса SharedPreferences, который позволяет работать с данными
        //Константа MODE_PRIVATE используется для настройки доступа и означает, что после сохранения, данные будут видны только этому приложению
        SharedPreferences.Editor ed = sharedPreferences.edit(); //чтобы редактировать данные, необходим объект Editor – он получается из sPref
        for (int i = 0; i < 15; i++)
        {
            String veg = "ve";
            ToggleButton toggleButton;
            toggleButton = listTB.get(i);
            int j = (i + 13);
            String str = Integer.toString(j);
            veg = veg.concat(str);
            System.out.println(veg);
            ed.putBoolean(veg, toggleButton.isChecked()); //В метод putString указывается наименование переменной – это константа SAVED_TEXT, и значение – содержимое поля etText
            ed.commit(); //Чтобы данные сохранились, необходимо выполнить commit
        }

        // Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show(); // выводит ссобщ, что данные сохранены
    }

    void loadSelection()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        for (int i = 0; i < 15; i++)
        {
            String veg = "ve";
            ToggleButton toggleButton;
            toggleButton = listTB.get(i);
            int j = (i + 13);
            String str = Integer.toString(j);
            veg = veg.concat(str);
            boolean savedSelection1 = sharedPreferences.getBoolean(veg, false);
            toggleButton.setChecked(savedSelection1);
        }
        // Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
         saveSelection();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        // TODO Auto-generated method stub
        // super.onBackPressed();
        saveSelection();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}
