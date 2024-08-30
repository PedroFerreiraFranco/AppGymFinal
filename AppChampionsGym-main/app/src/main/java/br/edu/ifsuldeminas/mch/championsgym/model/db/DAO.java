package br.edu.ifsuldeminas.mch.championsgym.model.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import br.edu.ifsuldeminas.mch.championsgym.model.db.DBHandler;

abstract class DAO {
    private static DBHandler dbHandler = null;

    DAO(Context context) {
        if (dbHandler == null) {
            dbHandler = new DBHandler(context);
        }
    }

    protected SQLiteDatabase openToWrite() throws SQLException {
        return dbHandler.getWritableDatabase();
    }

    protected SQLiteDatabase openToRead() throws SQLException {
        return dbHandler.getReadableDatabase();
    }
}
