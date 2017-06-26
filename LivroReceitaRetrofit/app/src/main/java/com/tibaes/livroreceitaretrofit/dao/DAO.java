package com.tibaes.livroreceitaretrofit.dao;

import java.util.List;

/**
 * Created by Juliana Tibães on 19/05/2017.
 * MeuAdvogado
 * com.tibaes.meuadvogado.dao
 */

public interface DAO<T> {

    public void inserir(T t);
    public void alterar(T t);
    public void excluir(T t);
    public List<T> pesquisar();
}
