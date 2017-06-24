package com.tibaes.livroreceitasqlite.task;

import android.os.AsyncTask;
import android.util.Log;

import com.tibaes.livroreceitasqlite.model.Receita;
import com.tibaes.livroreceitasqlite.util.ReceitaConverter;
import com.tibaes.livroreceitasqlite.util.WebClient;

/**
 * Created by Juliana Tibães on 18/06/2017.
 * LivroReceitaSQLite
 */

//AsyncTask permite que a requisição seja feita em uma thread separada da principal.
    // https://developer.android.com/reference/android/os/AsyncTask.html
    // http://www.devmedia.com.br/trabalhando-com-asynctask-no-android/33481
//TODO(1) criar uma classe para enviar os dados para o servidor fora da thread principal
public class InsereReceitaTask extends AsyncTask {

    private final Receita receita;

    public InsereReceitaTask(Receita receita) {

        this.receita = receita;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        String jsonReceita = new ReceitaConverter().converteParaJSON(receita);

        if(receita.getId()!=null) {
            new WebClient().atualiza(jsonReceita);
        }
        else {
            new WebClient().insere(jsonReceita);
        }
        return null;
    }

}
