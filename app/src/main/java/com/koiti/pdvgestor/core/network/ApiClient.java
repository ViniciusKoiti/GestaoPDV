package com.koiti.pdvgestor.core.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class ApiClient {

    private final String baseUrl;
    private final boolean enableLogging;
    private Retrofit retrofit;

    public ApiClient(String baseUrl) {
        this(baseUrl, true); // logging habilitado por padrão
    }

    public ApiClient(String baseUrl, boolean enableLogging) {
        this.baseUrl = baseUrl;
        this.enableLogging = enableLogging;
    }

    /**
     * Cria e retorna instância do Retrofit
     */
    public Retrofit getClient() {
        if (retrofit == null) {
            retrofit = createRetrofit();
        }
        return retrofit;
    }

    private Retrofit createRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        // Adicionar logging apenas se habilitado
        if (enableLogging) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    /**
     * Cria serviços de API
     */
    public <T> T createService(Class<T> serviceClass) {
        return getClient().create(serviceClass);
    }
}