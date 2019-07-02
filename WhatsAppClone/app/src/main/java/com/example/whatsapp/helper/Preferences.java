package com.example.whatsapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Preferences {
    private Context contexto;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO = "whatsapp.preferencias";
    private int MODE = 0;
    private SharedPreferences.Editor editor;

    private String CHAVE_IDENTIFICADOR = "identificadorUsuarioLogado";
    private String CHAVE_NOME = "nomeUsuarioLogado";
    /*
    private String CHAVE_NOME = "nome";
    private String CHAVE_TELEFONE = "telefone";
    private String CHAVE_TOKEN = "token";
    */

    public Preferences( Context contextoParametro ){
        contexto = contextoParametro;
        preferences = contexto.getSharedPreferences(NOME_ARQUIVO, MODE );
        editor = preferences.edit();

    }

    public void salvarDados(String identificadorUsuarioLogado, String nomeUsuario/*, String telefone, String token*/) {

        editor.putString(CHAVE_IDENTIFICADOR, identificadorUsuarioLogado);
        editor.putString(CHAVE_NOME, nomeUsuario);
        /*
        editor.putString(CHAVE_TELEFONE, telefone);
        editor.putString(CHAVE_TOKEN, token);
        */
        editor.commit();

    }

    /*
    public HashMap<String, String> getDadosUsuario() {
        HashMap<String, String> dadosUsuario = new HashMap<>();

        dadosUsuario.put(CHAVE_NOME, preferences.getString(CHAVE_NOME, null));
        dadosUsuario.put(CHAVE_TELEFONE, preferences.getString(CHAVE_TELEFONE, null));
        dadosUsuario.put(CHAVE_TOKEN, preferences.getString(CHAVE_TOKEN, null));

        return dadosUsuario;
    }
    */

    public String getIdentificador(){
        return preferences.getString(CHAVE_IDENTIFICADOR, null);
    }

    public String getNome(){
        return preferences.getString(CHAVE_NOME, null);
    }
}
