package br.edu.ifsuldeminas.mch.championsgym;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FormCadastro extends AppCompatActivity {
    EditText editTextEmail, editTextSenha;
    Button btnRegistrar;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual = mAuth.getCurrentUser();
        if(usuarioAtual !=null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.edit_email);
        editTextSenha = findViewById(R.id.edit_senha);
        btnRegistrar = findViewById(R.id.bt_cadastrar);
        progressBar = findViewById(R.id.progressBar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, senha;
                email = String.valueOf(editTextEmail.getText());
                senha = String.valueOf(editTextSenha.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(FormCadastro.this, "Digite o email", Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(senha)){
                    Toast.makeText(FormCadastro.this, "Digite a senha", Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(FormCadastro.this, "Conta Criada",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), FormLogin.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(FormCadastro.this, "Erro  de Autenticação",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });
    }
}