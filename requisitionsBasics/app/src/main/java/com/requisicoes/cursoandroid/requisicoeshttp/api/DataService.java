package com.requisicoes.cursoandroid.requisicoeshttp.api;

import com.requisicoes.cursoandroid.requisicoeshttp.model.Foto;
import com.requisicoes.cursoandroid.requisicoeshttp.model.Postagem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("/photos")
    Call<List<Foto>> recuperarFotos();

    @GET("/posts")
    Call<List<Postagem>> recuperarPostagens();

}
