package com.example.autenticacaousuario;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String email = "jamilton.cursos@gmail.com";
        String senha = "jamilton123";
        firebaseAuth = FirebaseAuth.getInstance();

        /*
        //Login do usuário
        firebaseAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() ) {
                    Log.i("signin", "Sucesso ao fazer login do usuário!!!");
                } else {
                    Log.i("signin", "Erro ao fazer login usuário!!");
                }
            }
        });
        */

        //delogar usuário
        firebaseAuth.signOut();

        //Verificar usuario logadp
        if(firebaseAuth.getCurrentUser() != null) {
            Log.i("verificaUsuario", "Usuario esta logado!!");
        } else {
            Log.i("verificaUsuario", "Usuário não está logado!!");
        }

        /*
        //cadastro
        firebaseAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful()) {
                    Log.i("createUser", "Sucesso ao cadastrar usuário");
                } else {
                    Log.i("createUser", "Erro ao cadastrar usuário!!!");
                }
            }
        });
        */
    }
}
