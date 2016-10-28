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
public class Seafood extends Activity {

    public static final String APP_PREFERENCES = "mysettings";


    ArrayList<ToggleButton> listTB = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seafood);

        listTB.add((ToggleButton) findViewById(R.id.gr_109));
        listTB.add((ToggleButton) findViewById(R.id.gr_110));
        listTB.add((ToggleButton) findViewById(R.id.gr_111));
        listTB.add((ToggleButton) findViewById(R.id.gr_112));
        listTB.add((ToggleButton) findViewById(R.id.gr_113));
        listTB.add((ToggleButton) findViewById(R.id.sf_90));
        listTB.add((ToggleButton) findViewById(R.id.sf_91));
        listTB.add((ToggleButton) findViewById(R.id.sf_92));
        listTB.add((ToggleButton) findViewById(R.id.sf_93));
        listTB.add((ToggleButton) findViewById(R.id.sf_94));
        listTB.add((ToggleButton) findViewById(R.id.sf_95));
        listTB.add((ToggleButton) findViewById(R.id.sf_96));
        listTB.add((ToggleButton) findViewById(R.id.sf_97));
        listTB.add((ToggleButton) findViewById(R.id.sf_98));
        listTB.add((ToggleButton) findViewById(R.id.sf_99));
        listTB.add((ToggleButton) findViewById(R.id.sf_100));
        listTB.add((ToggleButton) findViewById(R.id.sf_101));
        listTB.add((ToggleButton) findViewById(R.id.sf_102));
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
        for (int i = 0; i < 18; i++)
        {
            String veg = "sf";
            ToggleButton toggleButton;
            toggleButton = listTB.get(i);
            int j = (i + 85);
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
        for (int i = 0; i < 18; i++)
        {
            String veg = "sf";
            ToggleButton toggleButton;
            toggleButton = listTB.get(i);
            int j = (i + 85);
            String str = Integer.toString(j);
            veg = veg.concat(str);
            boolean savedSelection1 = sharedPreferences.getBoolean(veg, false);
            toggleButton.setChecked(savedSelection1);
        }
        // Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        saveSelection();
        Intent intent = new Intent(Seafood.this, MainActivity.class);
        startActivity(intent);
    }

}
