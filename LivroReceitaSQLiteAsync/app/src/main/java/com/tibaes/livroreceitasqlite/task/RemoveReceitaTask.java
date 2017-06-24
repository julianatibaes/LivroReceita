package com.tibaes.livroreceitasqlite.task;

import android.os.AsyncTask;

import com.tibaes.livroreceitasqlite.model.Receita;
import com.tibaes.livroreceitasqlite.util.ReceitaConverter;
import com.tibaes.livroreceitasqlite.util.WebClient;

/**
 * Created by Juliana Tibães on 23/06/2017.
 * LivroReceitaSQLite
 */

public class RemoveReceitaTask extends AsyncTask {

    private final Receita receita;

    public RemoveReceitaTask(Receita receita) {
        this.receita = receita;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        new WebClient().exclui(receita.getId());
        return null;
    }
}
