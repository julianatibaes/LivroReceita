package com.tibaes.livroreceitasqlite.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.UUID;

/**
 * Created by Juliana Tibães on 19/05/2017.
 */

public class DatabaseFactory extends SQLiteOpenHelper {

    private static final String DATABASE = "receita_db";

    public static final String RECEITA_TABLE = "receita_table";

    // tabela RECEITA
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String INGREDIENTES = "ingredientes";
    public static final String MODO_PREPARO = "modo_preparo";
    public static final String TEMPO_PREPARO = "tempo_preparo";
    public static final String QUANTIDADE_PORCOES = "quantidade_porcoes";
    public static final String NIVEL_DOFICULDADE = "nivel_dificuldade";
    public static final String CAMINHO_FOTO = "caminho_foto";


    public DatabaseFactory(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS "+RECEITA_TABLE+"( " +
                ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                NOME+" TEXT NOT NULL, " +
                INGREDIENTES+" TEXT, " +
                MODO_PREPARO+" TEXT, " +
                TEMPO_PREPARO+" INTEGER, "+
                QUANTIDADE_PORCOES+" INTEGER, "+
                NIVEL_DOFICULDADE+" REAL, "+
                CAMINHO_FOTO+" TEXT "+
                ");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+RECEITA_TABLE+";";
        db.execSQL(sql);
    }

}
