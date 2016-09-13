package com.example.artem.cw;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Artem on 13.09.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    // имя базы данных
    private static final String DATABASE_NAME = "mydatabase.db";
    // названия столбцов
    private static final String DATABASE_TABLE = "product";

    public static final String ID = "id";
    public static final String ID_PRODUCT_COLUMN = "id_product";
    public static final String CATEGORY_PRODUCT_COLUMN = "category";
    public static final String CHOICE_PRODUCT_COLUMN = "choice";
    // версия базы данных
    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " (" + ID
            + " integer primary key autoincrement, " + ID_PRODUCT_COLUMN
            + " text not null, " + CATEGORY_PRODUCT_COLUMN + " integer, " +CHOICE_PRODUCT_COLUMN
            + " integer);";



    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Запишем в журнал
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
        // Создаём новую таблицу
        onCreate(db);
    }
}
