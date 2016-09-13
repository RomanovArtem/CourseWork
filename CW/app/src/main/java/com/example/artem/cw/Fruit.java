package com.example.artem.cw;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Artem on 05.09.2016.
 */

public class Fruit extends Activity {

    ArrayList<ToggleButton> listTB = new ArrayList();
    ArrayList<Boolean> listCheck = new ArrayList();

    public void abc()
    {
        listTB.add((ToggleButton) findViewById(R.id.f1));
        listTB.add((ToggleButton) findViewById(R.id.f2));
        listTB.add((ToggleButton) findViewById(R.id.f3));
    }

    public List<Boolean> Search()
    {
        for (int i = 0; i < 2; i++)
        {
            ToggleButton toggleButton;
            toggleButton = listTB.get(i);
            listCheck.add(i, toggleButton.isChecked());
        }
        return listCheck;
    }

   // ToggleButton toogleButton;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fruit);
        loadSelection(listCheck, listTB);
        abc();







       // etText = (EditText) findViewById(R.id.etText);
       // toogleButton = (ToggleButton) findViewById(R.id.f1);
       // loadSelection();
    }

    public void onToggleButtonClick (View button){
        getApplicationContext();
       // aaa();
        /*Toast.makeText(
                getApplicationContext(),
                Boolean.toString(((ToggleButton) button).isChecked()),
                Toast.LENGTH_SHORT).show();*/
    }


    //EditText etText;
    SharedPreferences sPref;

   /* final String SAVED_TEXT = "saved_text";

   /* void aaa()
    {
        if (toogleButton.isChecked() == true)
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Пора покормить кота!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(toogleButton.isChecked() == false)
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Не Пора покормить кота!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }*/



    void saveSelection()
    {
        List<Boolean> list = Search();
        for (int i = 0; i < list.size(); i++) {
            sPref = getPreferences(MODE_PRIVATE); //получаем объект sPref класса SharedPreferences, который позволяет работать с данными
            //Константа MODE_PRIVATE используется для настройки доступа и означает, что после сохранения, данные будут видны только этому приложению
            SharedPreferences.Editor ed = sPref.edit(); //чтобы редактировать данные, необходим объект Editor – он получается из sPref
            Boolean button  = list.get(i);
            ed.putBoolean(String.valueOf(i), button.booleanValue()); //В метод putString указывается наименование переменной – это константа SAVED_TEXT, и значение – содержимое поля etText
            ed.commit(); //Чтобы данные сохранились, необходимо выполнить commit
            // Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show(); // выводит ссобщ, что данные сохранены
        }
    }

   void loadSelection(List<Boolean> listB, List<ToggleButton> listTB)
    {
        for (int i = 0; i < listB.size(); i++) {
            sPref = getPreferences(MODE_PRIVATE);
            Boolean buttona  = listB.get(i);
            boolean savedSelection = sPref.getBoolean(String.valueOf(i), buttona.booleanValue());
            ToggleButton button  = listTB.get(i);
            button.setChecked(savedSelection);
            // Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
        }
    }




    public void onClick(View view) {
        //Search();
       saveSelection();



        //Search();
        Intent intent = new Intent(Fruit.this, MainActivity.class);
        startActivity(intent);
    }

}
