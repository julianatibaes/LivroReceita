package com.tibaes.livroreceitaretrofit.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Juliana Tibães on 26/06/2017.
 * LivroReceitaRetrofit
 */

public class ReceitaRetrofit {

    private final Retrofit retrofit;

    public static final String IP = "http://172.16.196.160";

    public ReceitaRetrofit() {

        // faz a conexão com o web service

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        this.retrofit = new Retrofit.Builder().baseUrl(IP+":8080/WebServiceRest/" +
                "rest/service/")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client.build())
                .build();
    }

    // para acessar os métodos definidos na interface ReceitaServiceI
    public ReceitaServiceI getReceitaService()
    {
        return retrofit.create(ReceitaServiceI.class);
    }

}
