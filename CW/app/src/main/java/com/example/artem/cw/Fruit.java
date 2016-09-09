package com.example.artem.cw;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.artem.cw.MainActivity;
import com.example.artem.cw.R;


/**
 * Created by Artem on 05.09.2016.
 */

public class Fruit extends Activity {
    ToggleButton toogleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fruit);
       // etText = (EditText) findViewById(R.id.etText);
        toogleButton = (ToggleButton) findViewById(R.id.fruit_ananas);
        loadSelection();
    }

    public void onToggleButtonClick (View button){
        getApplicationContext();
        aaa();
        /*Toast.makeText(
                getApplicationContext(),
                Boolean.toString(((ToggleButton) button).isChecked()),
                Toast.LENGTH_SHORT).show();*/
    }


    //EditText etText;
    SharedPreferences sPref;

    final String SAVED_TEXT = "saved_text";

    void aaa()
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
    }

    void saveSelection()
    {
        sPref = getPreferences(MODE_PRIVATE); //получаем объект sPref класса SharedPreferences, который позволяет работать с данными
                                                //Константа MODE_PRIVATE используется для настройки доступа и означает, что после сохранения, данные будут видны только этому приложению
        SharedPreferences.Editor ed = sPref.edit(); //чтобы редактировать данные, необходим объект Editor – он получается из sPref
        ed.putBoolean(SAVED_TEXT, toogleButton.isChecked()); //В метод putString указывается наименование переменной – это константа SAVED_TEXT, и значение – содержимое поля etText
        ed.commit(); //Чтобы данные сохранились, необходимо выполнить commit
       // Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show(); // выводит ссобщ, что данные сохранены
    }

    void loadSelection()
    {
        sPref = getPreferences(MODE_PRIVATE);

        boolean savedSelection = sPref.getBoolean(SAVED_TEXT, false);
        toogleButton.setChecked(savedSelection);
       // Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        saveSelection();
        Intent intent = new Intent(Fruit.this, MainActivity.class);
        startActivity(intent);
    }

}
