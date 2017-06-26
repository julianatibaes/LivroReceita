package com.tibaes.livroreceitaretrofit.service;

import com.tibaes.livroreceitaretrofit.model.Receita;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Juliana Tibães on 25/06/2017.
 * LivroReceitaRetrofit
 */

public interface ReceitaService {

    @POST("cadastrar")
    Call<Void> insere(@Body Receita receita);

    @POST("alterar")
    Call<Void> altera(@Body Receita receita);

    @DELETE("excluir/{id}")
    Call<Void>  exclui(@Path("id") String id);

    @GET("todasReceitas")
    Call<List<Receita>> lista();
}
