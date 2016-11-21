package com.example.artem.cw;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Artem on 21.11.2016.
 */
public class Favourites extends AppCompatActivity {
    public static final String APP_PREFERENCES = "myselect";

    DBHelper dbHelper;
    SQLiteDatabase database;
    int margin = 130;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites);

        SelectRecipes();
    }

    public void SelectRecipes() {

        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase(); // открыть и вернуть экземпляр базы данных для работы
        // если БД нет - то в вызывает onCreate, если версия БД изменилась - onCreate
        ContentValues contentValues = new ContentValues(); // для добавления новых строк в таблицу

        Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null); //чтенеие всех записей из таблицы
        if (cursor.moveToFirst()) { // перемемещает курсор на первую строку в рез-те запроса // и проверяет есть ли вообще записи в курсоре
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int recipeIndex = cursor.getColumnIndex(DBHelper.KEY_RECIPE);

            do {
                CreateTextView(cursor.getString(nameIndex));
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", name = " + cursor.getString(nameIndex) +
                        ", recipe = " + cursor.getString(recipeIndex));
            } while (cursor.moveToNext());
        }
        else
            Log.d("mLog", "0 rows");

        cursor.close(); // закрываем для освобождения памяти
    }

    public void CreateTextView(String title) {
        TextView textView = null;
        RelativeLayout lMain;
        lMain = (RelativeLayout) findViewById(R.id.fav);
        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        lParams.topMargin = margin;
        margin += lParams.topMargin;

        TextView titleCan = new TextView(this);
        titleCan.setText(title);
        lMain.addView(titleCan, lParams);
    }

    public void ClickImageButton(View view) {
        database.delete(DBHelper.TABLE_NAME, null, null);

        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.clear();
        ed.commit();

        Toast toast = Toast.makeText(getApplicationContext(),
                "Вы удалили все рецепты из избранных!",
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
