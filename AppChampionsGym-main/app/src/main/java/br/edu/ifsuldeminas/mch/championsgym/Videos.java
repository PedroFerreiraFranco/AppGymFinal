package br.edu.ifsuldeminas.mch.championsgym;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsuldeminas.mch.championsgym.Exercise;
import br.edu.ifsuldeminas.mch.championsgym.R;

public class Videos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos); // Atualize o layout para activity_videos
        getSupportActionBar().hide();
        ListView listView = findViewById(R.id.listView);

        // Lista de exercícios com nome, descrição e URI do vídeo
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Puxada Costas", "Exercício deve ser feito com o tronco direcionado para o solo. Ótimo para fortalecimento da lombar e criação de músculo na região inferior das costas", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video01)));
        exercises.add(new Exercise("Agachamento Livre", "O agachamento livre é um exercício fundamental para a parte inferior do corpo, focando quadríceps, glúteos e isquiotibiais. Mantenha a postura correta para evitar lesões.", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video01)));
        exercises.add(new Exercise("Flexão de Braços", "A flexão de braços é um exercício clássico para fortalecer a parte superior do corpo, especialmente peitorais, tríceps e ombros. Mantenha o corpo alinhado durante o movimento.", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video01)));


        VideoAdapter adapter = new VideoAdapter(this, exercises);
        listView.setAdapter(adapter);
    }
}
