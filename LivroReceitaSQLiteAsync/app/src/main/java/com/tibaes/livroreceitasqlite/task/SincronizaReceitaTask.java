package com.tibaes.livroreceitasqlite.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.widget.Toast;

import com.tibaes.livroreceitasqlite.ReceitasActivity;
import com.tibaes.livroreceitasqlite.SplashActivity;
import com.tibaes.livroreceitasqlite.dao.ReceitaDAO;
import com.tibaes.livroreceitasqlite.model.Receita;
import com.tibaes.livroreceitasqlite.util.ReceitaConverter;
import com.tibaes.livroreceitasqlite.util.WebClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juliana Tibães on 09/06/2017.
 * LivroReceitaSQLite
 */

public class SincronizaReceitaTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private ProgressDialog dialog;
    List<Receita> receitaList;

    public SincronizaReceitaTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Sincronizando suas receitas...", true, true);
    }

    @Override
    protected String doInBackground(Void... params) {

        WebClient client = new WebClient();
        String receitaListJSON = client.buscaTodos();

        ReceitaConverter conversor = new ReceitaConverter();
        conversor.recebeListaJSON(receitaListJSON);
        ReceitaDAO dao = new ReceitaDAO(context);
        dao.sincroniza(receitaList);
        dao.close();

        return null;
    }

    @Override
    protected void onPostExecute(String resposta) {
        super.onPostExecute(resposta);
        dialog.dismiss();
        Toast.makeText(context, resposta, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(context, ReceitasActivity.class);

        context.startActivity(intent);



    }
}
