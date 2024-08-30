package br.edu.ifsuldeminas.mch.championsgym.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsuldeminas.mch.championsgym.model.Treino;

public class TreinoDAO extends DAO {

    public TreinoDAO(Context context) {
        super(context);
    }

    public boolean save(Treino treino) {
        SQLiteDatabase database = openToWrite();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nomeExercicio", treino.getNomeExercicio());
        contentValues.put("duracao", treino.getDuracao());
        contentValues.put("dataTreino", treino.getDataTreino());

        long result = database.insert("treinos", null, contentValues);
        database.close();

        if (result != -1) {
            // Atualiza o ID do treino com o ID gerado pelo banco
            treino.setId((int) result);
            return true;
        } else {
            return false;
        }
    }

    public List<Treino> loadTreinos() {
        SQLiteDatabase database = openToRead();
        List<Treino> treinos = new ArrayList<>();
        String sql = "SELECT * FROM treinos";
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nomeExercicio = cursor.getString(cursor.getColumnIndexOrThrow("nomeExercicio"));
                String duracao = cursor.getString(cursor.getColumnIndexOrThrow("duracao"));
                String dataTreino = cursor.getString(cursor.getColumnIndexOrThrow("dataTreino"));
                Treino treino = new Treino(id, nomeExercicio, duracao, dataTreino);
                treinos.add(treino);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return treinos;
    }

    public void delete(Treino treino) {
        SQLiteDatabase database = openToWrite();
        String[] params = {treino.getId().toString()};
        database.delete("treinos", "id = ?", params);
        database.close();
    }

    public void update(Treino treino) {
        SQLiteDatabase database = openToWrite();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nomeExercicio", treino.getNomeExercicio());
        contentValues.put("duracao", treino.getDuracao());
        contentValues.put("dataTreino", treino.getDataTreino());

        String[] params = {treino.getId().toString()};
        database.update("treinos", contentValues, "id = ?", params);
        database.close();
    }
}
