package com.requisicoes.cursoandroid.requisicoeshttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.requisicoes.cursoandroid.requisicoeshttp.api.CEPService;
import com.requisicoes.cursoandroid.requisicoeshttp.api.DataService;
import com.requisicoes.cursoandroid.requisicoeshttp.model.CEP;
import com.requisicoes.cursoandroid.requisicoeshttp.model.Foto;
import com.requisicoes.cursoandroid.requisicoeshttp.model.Postagem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button botaoRecuperar;
    private TextView textoResultado;
    private Retrofit retrofit;
    //private List<Foto> listaFotos = new ArrayList<>();
    private List<Postagem> listaPostagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoRecuperar = findViewById(R.id.buttonRecuperar);
        textoResultado = findViewById(R.id.textResultado);

        retrofit = new Retrofit.Builder()
                //aqui tem o URL base e no model tem o resto da url
                .baseUrl("https://jsonplaceholder.typicode.com")
                //.baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        botaoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recuperarCEPRetrofit();

                recuperarListaRetrofit();

                /*
                MyTask task = new MyTask();
                String urlApi = "https://blockchain.info/ticker";
                String cep = "01310100";
                String urlCep = "https://viacep.com.br/ws/" + cep + "/json/";
                task.execute(urlCep);
                 */
            }
        });

    }

    private void recuperarListaRetrofit() {
        DataService service = retrofit.create(DataService.class);
        //Call<List<Foto>> call = service.recuperarFotos();
        Call<List<Postagem>> call = service.recuperarPostagens();

        call.enqueue(new Callback<List<Postagem>>() {
            @Override
            public void onResponse(Call<List<Postagem>> call, Response<List<Postagem>> response) {
                if(response.isSuccessful()) {
                    listaPostagens = response.body();

                    for(int i=0; i<listaPostagens.size(); i++){
                        Postagem postagem = listaPostagens.get(i);
                        Log.d("resultado","resutado:"+ postagem.getId() + " / " + postagem.getTitle());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Postagem>> call, Throwable t) {

            }
        });
    }

    private void recuperarCEPRetrofit(){

        //ao acessar recuperarCEP() tem-se a chamada que o retrofit vai fazer
        CEPService cepService = retrofit.create(CEPService.class);
        Call<CEP> call = cepService.recuperarCEP("01310100");

        //ao usar o metodo enqueue é criada uma tarefa assincrona com facilidade
        //, automaticamente é criada uma tarefa assincrona dentro de uma thread para
        //fazer o download das informações
        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                    if(response.isSuccessful()){
                        CEP cep = response.body();
                        textoResultado.setText(cep.getLogradouro()+" / "+cep.getBairro());
                    }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });
    }

    class MyTask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String stringUrl = strings[0];
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            StringBuffer buffer = null;

            try {

                URL url = new URL(stringUrl);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                // Recupera os dados em Bytes
                inputStream = conexao.getInputStream();

                //inputStreamReader lê os dados em Bytes e decodifica para caracteres
                inputStreamReader = new InputStreamReader( inputStream );

                //Objeto utilizado para leitura dos caracteres do InpuStreamReader
                BufferedReader reader = new BufferedReader( inputStreamReader );
                buffer = new StringBuffer();
                String linha = "";

                while((linha = reader.readLine()) != null){
                    buffer.append( linha );
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);




            /*** Processamento JSON ***/
            String logradouro = null;
            String cep = null;
            String complemento = null;
            String bairro = null;
            String localidade = null;
            String uf = null;

            try {
                JSONObject jsonObject = new JSONObject(resultado);
                logradouro = jsonObject.getString("logradouro");
                cep = jsonObject.getString("cep");
                complemento = jsonObject.getString("complemento");
                bairro = jsonObject.getString("bairro");
                localidade = jsonObject.getString("localidade");
                uf = jsonObject.getString("uf");
                //precisa formatar a string e printar apenas


                /*
                    se tiver um outro item a recuperar dentro de outro item exemplo:
                    "BRL" : {"15m" : 1093.06, "last" : 1093.06, "buy" : 1092.77, "sell" : 1093.06,  "symbol" : "R$"},

                     String objetoValor = null;
                     String valorMoeda = null;
                     String simbolo = null;

                     JSONObject jsonObject = new JSONObject(resultado);
                     objetoValor = jsonObject.getString( "BLR" );

                      embaixo é criado um novo objet e com ele é possivel acessar o last e symbol
                     JSONObject jsonObjectReal = new JSONObject( objetoValor );
                     valorMoeda = jsonObjectReal.getString( "last" );
                     simbolo = jsonObjectReal.getString( "symbol" );


                 */
            } catch (JSONException e) {
                e.printStackTrace();
            }

            /*** Processamento JSON ***/




            textoResultado.setText( resultado );
        }
    }

}
