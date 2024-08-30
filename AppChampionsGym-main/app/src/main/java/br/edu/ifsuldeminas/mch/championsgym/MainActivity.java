package br.edu.ifsuldeminas.mch.championsgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import br.edu.ifsuldeminas.mch.championsgym.model.Treino;
import br.edu.ifsuldeminas.mch.championsgym.model.db.TreinoDAO;


public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button btTreinos;
    Button btVideos;
    Button btAPI;
    Button btDeslogar;
    TextView textView;
    FirebaseUser usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        btTreinos = findViewById(R.id.bt_treinos);
        btVideos = findViewById(R.id.bt_videos);
        btDeslogar = findViewById(R.id.bt_deslogar);
        btAPI = findViewById(R.id.bt_API);
        textView = findViewById(R.id.text_dadosUsuario);
        usuario = auth.getCurrentUser();
        if(usuario == null){
            Intent intent = new Intent(getApplicationContext(), FormLogin.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText(usuario.getEmail());
        }

        btTreinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormTreino.class);
                startActivity(intent);
            }
        });

        btVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Videos.class);
                startActivity(intent);
            }
        });

        btAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, API_activity.class);
                startActivity(intent);
            }
        });

        btDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), FormLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}