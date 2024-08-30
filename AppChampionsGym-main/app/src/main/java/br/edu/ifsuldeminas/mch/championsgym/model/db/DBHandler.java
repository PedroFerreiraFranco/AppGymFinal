package br.edu.ifsuldeminas.mch.championsgym.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "treinos.db";
    private static final int DATABASE_VERSION = 2;

    // Tabela TREINOS
    private static final String TABLE_TREINOS_CREATE_SQL =
            "CREATE TABLE IF NOT EXISTS treinos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nomeExercicio TEXT, " +
                    "duracao TEXT, " +
                    "dataTreino TEXT)";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_TREINOS_CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS treinos");
        onCreate(sqLiteDatabase);
    }
}
