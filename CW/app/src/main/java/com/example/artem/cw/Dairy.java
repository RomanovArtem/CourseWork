package com.example.artem.cw;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by Artem on 24.10.2016.
 */
public class Dairy extends Activity {

    public static final String APP_PREFERENCES = "mysettings";


    ArrayList<ToggleButton> listTB = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dairy);

        listTB.add((ToggleButton) findViewById(R.id.d_36));
        listTB.add((ToggleButton) findViewById(R.id.d_37));
        listTB.add((ToggleButton) findViewById(R.id.d_38));
        listTB.add((ToggleButton) findViewById(R.id.d_39));
        listTB.add((ToggleButton) findViewById(R.id.d_40));
        listTB.add((ToggleButton) findViewById(R.id.d_41));
        listTB.add((ToggleButton) findViewById(R.id.d_42));
        listTB.add((ToggleButton) findViewById(R.id.d_43));
        listTB.add((ToggleButton) findViewById(R.id.d_44));
        listTB.add((ToggleButton) findViewById(R.id.d_45));
        listTB.add((ToggleButton) findViewById(R.id.d_46));
        listTB.add((ToggleButton) findViewById(R.id.d_47));

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

    void saveSelection()
    {

        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE); //получаем объект sPref класса SharedPreferences, который позволяет работать с данными
        //Константа MODE_PRIVATE используется для настройки доступа и означает, что после сохранения, данные будут видны только этому приложению
        SharedPreferences.Editor ed = sharedPreferences.edit(); //чтобы редактировать данные, необходим объект Editor – он получается из sPref
        for (int i = 0; i < 12; i++)
        {
            String veg = "d";
            ToggleButton toggleButton;
            toggleButton = listTB.get(i);
            int j = (i + 36);
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
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES , MODE_PRIVATE);
        for (int i = 0; i < 12; i++)
        {
            String veg = "d";
            ToggleButton toggleButton;
            toggleButton = listTB.get(i);
            int j = (i + 36);
            String str = Integer.toString(j);
            veg = veg.concat(str);
            boolean savedSelection1 = sharedPreferences.getBoolean(veg, false);
            toggleButton.setChecked(savedSelection1);
        }
        // Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        saveSelection();
        Intent intent = new Intent(Dairy.this, MainActivity.class);
        startActivity(intent);
    }
}
