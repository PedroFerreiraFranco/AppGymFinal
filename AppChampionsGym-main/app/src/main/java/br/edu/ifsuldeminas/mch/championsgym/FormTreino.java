package br.edu.ifsuldeminas.mch.championsgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsuldeminas.mch.championsgym.model.Treino;
import br.edu.ifsuldeminas.mch.championsgym.model.db.TreinoDAO;

public class FormTreino extends AppCompatActivity {

    private ListView listViewTreinos;
    private ArrayAdapter<Treino> treinoAdapter;
    private List<Treino> treinoList;
    private ImageView imageViewShare, imageViewDelete;
    private Treino treinoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_treino);
        getSupportActionBar().hide();

        imageViewShare = findViewById(R.id.imageViewShare);
        imageViewDelete = findViewById(R.id.imageViewDelete);
        listViewTreinos = findViewById(R.id.listViewTreinos);
        treinoList = new ArrayList<>();

        treinoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, treinoList);
        listViewTreinos.setAdapter(treinoAdapter);

        listViewTreinos.setOnItemClickListener((parent, view, position, id) -> {
            treinoSelecionado = treinoList.get(position);
            // Exibir uma mensagem quando um treino for selecionado
            String mensagem = String.format("Treino selecionado: %s",
                    treinoSelecionado.getNomeExercicio(), treinoSelecionado.getDuracao(), treinoSelecionado.getDataTreino());
            Toast.makeText(FormTreino.this, mensagem, Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.bt_cadastrarTreino).setOnClickListener(v -> {
            Intent intent = new Intent(FormTreino.this, FormCadastroTreino.class);
            startActivityForResult(intent, 1);
        });

        carregarTreinos(); // Carregar treinos ao iniciar a atividade

        imageViewShare.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Qual o tipo de treino?");

            LayoutInflater inflater = getLayoutInflater();
            View layoutDialogView = inflater.inflate(R.layout.alert_dialog_treino_view, null);

            builder.setView(layoutDialogView);

            builder.setNegativeButton("Cancelar", null);

            builder.setPositiveButton("Compartilhar", (dialogLayout, button) -> {
                EditText editText = layoutDialogView.findViewById(R.id.editTextAlertDialogId);
                String treino = editText.getText().toString();

                if(treino.equals("")) {
                    Toast toast = Toast.makeText(this, "Tipo do treino não pode ser vazio!", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                String message = String.format
                        ("Treino de %s: Nome: %s, Duração: %s, Data: %s",
                                treino, treinoSelecionado.getNomeExercicio(), treinoSelecionado.getDuracao(), treinoSelecionado.getDataTreino());

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, "");
                startActivity(shareIntent);
            });

            builder.create().show();
        });

        imageViewDelete.setOnClickListener(view -> {
            if (treinoSelecionado != null) {
                new AlertDialog.Builder(this)
                        .setTitle("Excluir Treino")
                        .setMessage("Você tem certeza que deseja excluir o treino selecionado?")
                        .setPositiveButton("Sim", (dialog, which) -> excluirTreino(treinoSelecionado))
                        .setNegativeButton("Não", null)
                        .show();
            } else {
                Toast.makeText(this, "Nenhum treino selecionado!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Treino novoTreino = (Treino) data.getSerializableExtra("novoTreino");
            if (novoTreino != null) {
                treinoList.add(novoTreino);
                treinoAdapter.notifyDataSetChanged();
            }
        }
    }

    private void carregarTreinos() {
        TreinoDAO treinoDAO = new TreinoDAO(this);
        List<Treino> treinos = treinoDAO.loadTreinos();
        treinoList.clear();
        treinoList.addAll(treinos);
        treinoAdapter.notifyDataSetChanged();
    }

    private void excluirTreino(Treino treino) {
        TreinoDAO treinoDAO = new TreinoDAO(this);
        treinoDAO.delete(treino);
        treinoList.remove(treino);
        treinoAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Treino excluído com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
