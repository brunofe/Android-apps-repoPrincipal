package com.example.whatsapp.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.whatsapp.R;
import com.example.whatsapp.config.ConfiguracaoFirebase;
import com.example.whatsapp.helper.Base64Custom;
import com.example.whatsapp.helper.Preferences;
import com.example.whatsapp.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private Button botaoLogar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerUsuario;
    private String identificadorUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        verificarUsuarioLogado();

        email = findViewById(R.id.emailEditTextId);
        senha = findViewById(R.id.senhaEditTextId);
        botaoLogar = findViewById(R.id.buttonLogarId);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setEmail( email.getText().toString() );
                usuario.setSenha( senha.getText().toString() );
                validarLogin();
            }
        });

    }

    private void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutentication();
        if(autenticacao.getCurrentUser() != null) {
            abrirTelaPrincipal();
        }
    }

    private void validarLogin(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutentication();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {

                    identificadorUsuarioLogado = Base64Custom.codificarBase64(usuario.getEmail());

                    firebase = ConfiguracaoFirebase.getFirebase()
                            .child("usurios")
                            .child(identificadorUsuarioLogado );


                    valueEventListenerUsuario = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Usuario usuarioRecuperado = dataSnapshot.getValue(Usuario.class);

                            Preferences preferencias = new Preferences(LoginActivity.this);

                            preferencias.salvarDados(identificadorUsuarioLogado, usuarioRecuperado.getNome());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };

                    firebase.addListenerForSingleValueEvent(valueEventListenerUsuario );

                    abrirTelaPrincipal();
                    Toast.makeText(LoginActivity.this, "Sucesso ao fazer login !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Erro ao fazer login!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void abrirCadastroUsuario(View view) {
        Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }
}