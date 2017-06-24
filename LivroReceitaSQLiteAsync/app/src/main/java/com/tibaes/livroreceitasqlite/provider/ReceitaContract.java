package com.tibaes.livroreceitasqlite.provider;

import android.net.Uri;

/**
 * Created by Juliana Tibães on 23/06/2017.
 * LivroReceitaSQLite
 */

public class ReceitaContract {

    public static final String AUTHORITY = "com.tibaes.livroreceitasqlite.provider";
    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY + "/");


    public static final String RECEITA_TABLE = "receita_table";

    // tabela RECEITA
    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI,RECEITA_TABLE);

    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String INGREDIENTES = "ingredientes";
    public static final String MODO_PREPARO = "modo_preparo";
    public static final String TEMPO_PREPARO = "tempo_preparo";
    public static final String QUANTIDADE_PORCOES = "quantidade_porcoes";
    public static final String NIVEL_DOFICULDADE = "nivel_dificuldade";
    public static final String CAMINHO_FOTO = "caminho_foto";

}
