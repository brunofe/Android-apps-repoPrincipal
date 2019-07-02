package com.example.whatsapp.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private Button botaoCadastrar;
    private Usuario usuario;

    private FirebaseAuth autenticacao;
    private DatabaseReference firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = findViewById(R.id.edit_cadastro_nome_id);
        email = findViewById(R.id.edit_cadastro_emal_Id);
        senha = findViewById(R.id.edit_cadastro_senha_id);
        botaoCadastrar = findViewById(R.id.button_cadastrar_id);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                cadastrarUsuario();
            }
        });
    }

    private void cadastrarUsuario(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutentication();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroUsuarioActivity.this,"Sucesso ao cadastrar usuário", Toast.LENGTH_SHORT).show();

                    FirebaseUser usuarioFirebase= task.getResult().getUser();
                    //usuarioFirebase.getUid()

                    String identificadorUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setId(identificadorUsuario);
                    usuario.salvar();

                    Preferences preferencias = new Preferences(CadastroUsuarioActivity.this);
                    preferencias.salvarDados(identificadorUsuario, usuario.getNome());

                    abrirLoginUsuario();

                }else {
                    String erroExcecao="";

                    try{
                        throw task.getException();
                    } catch(FirebaseAuthWeakPasswordException e) {
                        erroExcecao="Digite uma senha mais forte, contendo no minimo 6 caracteres";
                        Log.e("Exception", "Exception: " + task.getException().getMessage());
                    } catch(FirebaseAuthInvalidCredentialsException e){
                        erroExcecao="O e-mail digitado é invalido, digite um novo e-mail!";
                        Log.e("Exception", "Exception: " + task.getException().getMessage());
                    } catch(FirebaseAuthUserCollisionException e) {
                        erroExcecao="O e-mail já está em uso no App!";
                        Log.e("Exception", "Exception: " + task.getException().getMessage());
                    } catch(Exception e) {
                        erroExcecao=task.getException().getMessage();
                        e.printStackTrace();
                        Log.e("Exception", "Exception: " + task.getException().getMessage());
                    }
                    Toast.makeText(CadastroUsuarioActivity.this,"Erro: "+erroExcecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void abrirLoginUsuario(){
        Intent intent = new Intent(CadastroUsuarioActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}