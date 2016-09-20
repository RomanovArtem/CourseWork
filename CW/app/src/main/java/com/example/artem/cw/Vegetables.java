package com.example.artem.cw;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

        listTB.add((ToggleButton) findViewById(R.id.v_1));
        listTB.add((ToggleButton) findViewById(R.id.v_2));
        listTB.add((ToggleButton) findViewById(R.id.v_3));
        listTB.add((ToggleButton) findViewById(R.id.v_4));
        listTB.add((ToggleButton) findViewById(R.id.v_5));
        listTB.add((ToggleButton) findViewById(R.id.v_6));
        listTB.add((ToggleButton) findViewById(R.id.v_7));
        listTB.add((ToggleButton) findViewById(R.id.v_8));
        listTB.add((ToggleButton) findViewById(R.id.v_9));
        listTB.add((ToggleButton) findViewById(R.id.v_10));
        listTB.add((ToggleButton) findViewById(R.id.v_11));
        listTB.add((ToggleButton) findViewById(R.id.v_12));
        listTB.add((ToggleButton) findViewById(R.id.v_13));
        listTB.add((ToggleButton) findViewById(R.id.v_14));
        listTB.add((ToggleButton) findViewById(R.id.v_15));

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
            String veg = "v";
            ToggleButton toggleButton;
            toggleButton = listTB.get(i);
            String str = Integer.toString(i);
            veg = veg.concat(str);
            ed.putBoolean(veg, toggleButton.isChecked()); //В метод putString указывается наименование переменной – это константа SAVED_TEXT, и значение – содержимое поля etText
            ed.commit(); //Чтобы данные сохранились, необходимо выполнить commit
        }

        // Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show(); // выводит ссобщ, что данные сохранены
    }

    void loadSelection()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES , MODE_PRIVATE);
        for (int i = 0; i < 15; i++)
        {
            String veg = "v";
            ToggleButton toggleButton;
            toggleButton = listTB.get(i);
            String str = Integer.toString(i);
            veg = veg.concat(str);
            boolean savedSelection1 = sharedPreferences.getBoolean(veg, false);
            toggleButton.setChecked(savedSelection1);
        }
        // Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        saveSelection();
        Intent intent = new Intent(Vegetables.this, MainActivity.class);
        startActivity(intent);
    }

}
