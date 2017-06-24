package com.tibaes.livroreceitasqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tibaes.livroreceitasqlite.model.Receita;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juliana Tibães on 09/06/2017.
 * LivroReceitaSQLite
 */

public class ReceitaDAO  extends DatabaseFactory implements DAO<Receita>{

    public ReceitaDAO(Context context) {
        super(context);
    }

    @NonNull
    private ContentValues getContentValues(Receita receita) {
        ContentValues dados = new ContentValues();
        dados.put(ID, receita.getId());
        dados.put(NOME, receita.getNome());
        dados.put(NIVEL_DOFICULDADE, receita.getNivelDificuldade());
        return dados;
    }

    @Override
    public void inserir(Receita receita) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(receita);
        db.insert(RECEITA_TABLE, null, dados);
    }

    @Override
    public void alterar(Receita receita) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(receita);

        String[] params ={receita.getId().toString()};
        db.update(RECEITA_TABLE, dados, ID + " = ?", params);
    }

    @Override
    public void excluir(Receita receita) {
        SQLiteDatabase db = getWritableDatabase();
        String [] params = {receita.getId().toString()};
        db.delete(RECEITA_TABLE, ID +" = ?", params);
    }

    @Override
    public List<Receita> pesquisar() {
        String sql = "SELECT * FROM "+RECEITA_TABLE+";";
        SQLiteDatabase db = getReadableDatabase();

        try {
            Cursor c = db.rawQuery(sql, null);
            Log.d("DEBUG: ", c.toString());
            List<Receita> list = new ArrayList<Receita>();
            while (c.moveToNext()) {
                Receita receita = new Receita();
                receita.setId(c.getInt(c.getColumnIndex(ID)));
                receita.setNome(c.getString(c.getColumnIndex(NOME)));
                receita.setNivelDificuldade(c.getFloat(c.getColumnIndex(NIVEL_DOFICULDADE)));
                list.add(receita);
            }
            c.close();
            return list;
        }
        catch (Exception e){
            Log.e("ERROR: ", e.getMessage());
            return null;
        }
    }



    public void sincronizar(List<Receita> receitaList){
        for(Receita receita: receitaList){
            if(existeTrue(receita)){
                inserir(receita);
            }else{
                alterar(receita);
            }
        }
    }

    private boolean existeTrue(Receita receita){
        String sql = "SELECT "+ID+" FROM "+RECEITA_TABLE+" WHERE "
                +ID+ "="+receita.getId() +";";

        SQLiteDatabase db = getWritableDatabase();

        try {
            Cursor cursor = db.rawQuery(sql, null);
            // se tiver algo no banco com aquele id ele vai retornar
            // pelo menos (é o esperado que seja exato) 1
            if(cursor.getCount()>0){
                return true;
            }
            else{
                return false;
            }
            } catch (Exception e){
             Log.e("LOG: ", e.getMessage());
         }
        return true;
    }



    /*public void sincroniza(List<Receita> receitaList) {

        for (int i = 0; i < receitaList.size(); i++) {

            if (existe(receitaList.get(i))) {
                alterar(receitaList.get(i));
            } else {
                inserir(receitaList.get(i));
            }
        }
    }
    */

    public boolean existe(Receita receita) {
        SQLiteDatabase db = getWritableDatabase();
        Log.d("LOG: ", receita.getId().toString());

        String existe = "SELECT "+ID+" FROM "+RECEITA_TABLE+" WHERE "+ID+" = "+receita.getId().toString()+";";


        try {
            Cursor cursor = db.rawQuery(existe, null);
            Integer quantidade = cursor.getCount();
            if (quantidade == null || quantidade ==0)
                return false;
            else
                return true;
        }catch (Exception e){
            Log.e("ERROR: ", e.getMessage());
            return false;
        }
    }
}
