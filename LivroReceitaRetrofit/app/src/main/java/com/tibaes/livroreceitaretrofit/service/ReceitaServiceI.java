package com.tibaes.livroreceitaretrofit.service;

import com.tibaes.livroreceitaretrofit.model.Receita;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Juliana Tibães on 26/06/2017.
 * LivroReceitaRetrofit
 */

public interface ReceitaServiceI {

    @POST("cadastrar")
    Call<Void> inserir(@Body Receita receita);

    @POST("alterar")
    Call<Void> alterar(@Body Receita receita);

    @DELETE("excluir/{id}")
    Call<Void> excluir(@Path("id") String id);

    @GET("todasReceitas")
    Call<List<Receita>> listar();
}
