package br.edu.ifsuldeminas.mch.championsgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import br.edu.ifsuldeminas.mch.championsgym.model.Treino;
import br.edu.ifsuldeminas.mch.championsgym.model.db.TreinoDAO;

public class FormCadastroTreino extends AppCompatActivity {

    private EditText editNomeTreino, editDuracao, editData;
    private Treino treino;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro_treino);

        getSupportActionBar().hide();

        editNomeTreino = findViewById(R.id.edit_nome_treino);
        editDuracao = findViewById(R.id.edit_duracao);
        editData = findViewById(R.id.edit_data);

        Intent intent = getIntent();
        treino = (Treino) intent.getSerializableExtra("treino");

        if (treino != null) {
            editNomeTreino.setText(treino.getNomeExercicio());
            editDuracao.setText(treino.getDuracao());
            editData.setText(treino.getDataTreino());
        }

        findViewById(R.id.bt_registrarTreino).setOnClickListener(v -> salvarTreino());
    }

    private void salvarTreino() {
        String nomeExercicio = editNomeTreino.getText().toString();
        String duracao = editDuracao.getText().toString();
        String dataTreino = editData.getText().toString();

        if (nomeExercicio.isEmpty() || duracao.isEmpty() || dataTreino.isEmpty()) {
            Toast.makeText(this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_LONG).show();
            return;
        }

        TreinoDAO treinoDAO = new TreinoDAO(this);

        if (treino == null) {
            treino = new Treino(0, nomeExercicio, duracao, dataTreino);
            treinoDAO.save(treino);
            Toast.makeText(this, "Treino cadastrado com sucesso!", Toast.LENGTH_LONG).show();
        } else {
            treino.setNomeExercicio(nomeExercicio);
            treino.setDuracao(duracao);
            treino.setDataTreino(dataTreino);
            treinoDAO.update(treino);
            Toast.makeText(this, "Treino atualizado com sucesso!", Toast.LENGTH_LONG).show();
        }

        Intent resultIntent = new Intent();
        resultIntent.putExtra("novoTreino", treino);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

}
