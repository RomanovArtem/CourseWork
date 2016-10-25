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

public class Supplements extends Activity
{
    public static final String APP_PREFERENCES = "mysettings";

    ArrayList<ToggleButton> listTB = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supplements);

        listTB.add((ToggleButton) findViewById(R.id.m_65));
        listTB.add((ToggleButton) findViewById(R.id.m_66));
        listTB.add((ToggleButton) findViewById(R.id.m_67));
        listTB.add((ToggleButton) findViewById(R.id.m_68));
        listTB.add((ToggleButton) findViewById(R.id.m_69));
        listTB.add((ToggleButton) findViewById(R.id.m_70));
        listTB.add((ToggleButton) findViewById(R.id.m_71));
        listTB.add((ToggleButton) findViewById(R.id.m_72));
        listTB.add((ToggleButton) findViewById(R.id.m_73));
        listTB.add((ToggleButton) findViewById(R.id.m_74));
        listTB.add((ToggleButton) findViewById(R.id.su_58));
        listTB.add((ToggleButton) findViewById(R.id.su_59));
        loadSelection();
    }

    public void onToggleButtonClick (View button){
        getApplicationContext();
    }

    void saveSelection()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES , MODE_PRIVATE); //получаем объект sPref класса SharedPreferences, который позволяет работать с данными
        //Константа MODE_PRIVATE используется для настройки доступа и означает, что после сохранения, данные будут видны только этому приложению
        SharedPreferences.Editor ed = sharedPreferences.edit(); //чтобы редактировать данные, необходим объект Editor – он получается из sPref
        for (int i = 0; i < 12; i++)
        {
            String fru = "su";
            ToggleButton toggleButton;
            toggleButton = listTB.get(i);
            int j = (i + 48);
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
        for (int i = 0; i < 12; i++)
        {
            String fru = "su";
            ToggleButton toggleButton;
            toggleButton = listTB.get(i);
            int j = (i + 48);
            String str = Integer.toString(j);
            fru = fru.concat(str);
            boolean savedSelection1 = sharedPreferences.getBoolean(fru, false);
            toggleButton.setChecked(savedSelection1);
        }
    }

    public void onClick(View view) {
        saveSelection();
        Intent intent = new Intent(Supplements.this, MainActivity.class);
        startActivity(intent);
    }
}
