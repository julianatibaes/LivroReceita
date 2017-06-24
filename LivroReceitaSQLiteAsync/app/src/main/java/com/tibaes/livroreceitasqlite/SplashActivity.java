package com.tibaes.livroreceitasqlite;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.fasterxml.jackson.core.JsonParser;
import com.tibaes.livroreceitasqlite.dao.ReceitaDAO;
import com.tibaes.livroreceitasqlite.model.Receita;
import com.tibaes.livroreceitasqlite.util.ReceitaConverter;
import com.tibaes.livroreceitasqlite.util.WebClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new SincronizaDadosAsync().execute();
    }

    /** classe queu vai rodar em background para carregar os
     *  dados do webservice e sincronizar com os dados do
     *  SqLite
     */
    private class SincronizaDadosAsync extends
            AsyncTask<Void, Void, Void>{

        List<Receita> receitaList;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            WebClient webClient = new WebClient();

            // Receber todos os dados em json que está no
            // no webservice
            String receitaListJSON = webClient.getTodasReceitas();

            // caso tenha dados no webservice, vamos sincronizar
            // com o banco de dados local (SqLite)
            if(receitaListJSON != null){
                try{
                    ReceitaConverter conversor = new ReceitaConverter();
                    receitaList = conversor.jsonToListaReceita(receitaListJSON);

                    // sincrinizar com o banco de dados local
                    ReceitaDAO receitaDAO = new ReceitaDAO(SplashActivity.this);
                    receitaDAO.sincronizar(receitaList);
                    receitaDAO.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Intent intent = new Intent(SplashActivity.this, ReceitasActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
