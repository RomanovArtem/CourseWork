package com.example.artem.cw;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

import java.util.ArrayList;


/**
 * Created by Artem on 05.09.2016.
 */

public class Greenery extends Activity
{
    public static final String APP_PREFERENCES = "mysettings";

    ArrayList<ToggleButton> listTB = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greenery);

        listTB.add((ToggleButton) findViewById(R.id.fr_1));
        listTB.add((ToggleButton) findViewById(R.id.fr_2));
        listTB.add((ToggleButton) findViewById(R.id.fr_3));
        listTB.add((ToggleButton) findViewById(R.id.fr_4));
        listTB.add((ToggleButton) findViewById(R.id.fr_5));
        listTB.add((ToggleButton) findViewById(R.id.gr_114));
        listTB.add((ToggleButton) findViewById(R.id.gr_115));
        listTB.add((ToggleButton) findViewById(R.id.gr_116));
        listTB.add((ToggleButton) findViewById(R.id.gr_117));
        listTB.add((ToggleButton) findViewById(R.id.gr_118));
        listTB.add((ToggleButton) findViewById(R.id.gr_119));
        listTB.add((ToggleButton) findViewById(R.id.gr_120));
        listTB.add((ToggleButton) findViewById(R.id.gr_121));
        listTB.add((ToggleButton) findViewById(R.id.gr_122));
        listTB.add((ToggleButton) findViewById(R.id.gr_123));
        listTB.add((ToggleButton) findViewById(R.id.gr_124));
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


    //SharedPreferences sPref;

    void saveSelection()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES , MODE_PRIVATE); //получаем объект sPref класса SharedPreferences, который позволяет работать с данными
        //Константа MODE_PRIVATE используется для настройки доступа и означает, что после сохранения, данные будут видны только этому приложению
        SharedPreferences.Editor ed = sharedPreferences.edit(); //чтобы редактировать данные, необходим объект Editor – он получается из sPref
        for (int i = 0; i < 16; i++)
        {
            String fru = "gr";
            ToggleButton toggleButton;
            toggleButton = listTB.get(i);
            int j = (i + 109);
            String str = Integer.toString(j);
            fru = fru.concat(str);
            ed.putBoolean(fru, toggleButton.isChecked()); //В метод putString указывается наименование переменной – это константа SAVED_TEXT, и значение – содержимое поля etText
            ed.commit(); //Чтобы данные сохранились, необходимо выполнить commit
        }

        // Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show(); // выводит ссобщ, что данные сохранены
    }

    void loadSelection()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES , MODE_PRIVATE);
        for (int i = 0; i < 16; i++)
        {
            String fru = "gr";
            ToggleButton toggleButton;
            toggleButton = listTB.get(i);
            int j = (i + 109);
            String str = Integer.toString(j);
            fru = fru.concat(str);
            boolean savedSelection1 = sharedPreferences.getBoolean(fru, false);
            toggleButton.setChecked(savedSelection1);
        }
        // Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        saveSelection();
        Intent intent = new Intent(Greenery.this, MainActivity.class);
        startActivity(intent);
    }
}
