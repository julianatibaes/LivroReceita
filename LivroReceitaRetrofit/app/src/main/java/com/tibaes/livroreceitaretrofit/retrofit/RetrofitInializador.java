package com.tibaes.livroreceitaretrofit.retrofit;


import com.tibaes.livroreceitaretrofit.service.ReceitaService;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Juliana Tibães on 25/06/2017.
 * LivroReceitaRetrofit
 */

public class RetrofitInializador {

    private final Retrofit retrofit;

    public static final String IP = "http://172.16.196.160";

    public RetrofitInializador() {


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        retrofit = new Retrofit.Builder().baseUrl(IP+":8080/WebServiceRest/rest/service/")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client.build())
                .build();

    }

    public ReceitaService getReceitaService() {
        return retrofit.create(ReceitaService.class);
    }

}
