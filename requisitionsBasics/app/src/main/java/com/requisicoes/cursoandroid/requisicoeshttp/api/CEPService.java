package com.requisicoes.cursoandroid.requisicoeshttp.api;

import com.requisicoes.cursoandroid.requisicoeshttp.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//é utilizada essa interface pra configurar as requisições
public interface CEPService {



    // Call(retrofit2), o retrofit trabalha com Calls(que sao chamadas ao servidor web)
    // ,"aquele processo de fazer uma requisição e em seguida capturar a resposta"
    // Dentro dessa Call precisa-se definir o retorno que geralmente é um "model"
    // (modelo) que aqui é class CEP.
    @GET("{cep}/json/")
    Call<CEP> recuperarCEP(@Path("cep") String cep);

}
