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
 * Created by Artem on 13.09.2016.
 */
public class SaveLoadSelection extends Fruit {

    SharedPreferences sPref;
    final String SAVED_TEXT = "saved_text";

    void saveSelection(ToggleButton toogleButton) {
        sPref = getPreferences(MODE_PRIVATE); //получаем объект sPref класса SharedPreferences, который позволяет работать с данными
        //Константа MODE_PRIVATE используется для настройки доступа и означает, что после сохранения, данные будут видны только этому приложению
        //SharedPreferences.Editor ed = sPref.edit(); //чтобы редактировать данные, необходим объект Editor – он получается из sPref
       // ed.putBoolean(SAVED_TEXT, toogleButton.isChecked()); //В метод putString указывается наименование переменной – это константа SAVED_TEXT, и значение – содержимое поля etText
       // ed.commit(); //Чтобы данные сохранились, необходимо выполнить commit
        // Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show(); // выводит ссобщ, что данные сохранены
    }

    void loadSelection() {
        sPref = getPreferences(MODE_PRIVATE);

        boolean savedSelection = sPref.getBoolean(SAVED_TEXT, false);
       // toogleButton.setChecked(savedSelection);
        // Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }
}