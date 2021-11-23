package com.oceanmtech.dmt.api;

import com.oceanmtech.dmt.Utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitClient {

        private static Retrofit retrofit = null;

        public static Retrofit getApiClient() {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.addInterceptor(httpLoggingInterceptor);
            client.connectTimeout(60, TimeUnit.SECONDS);
            client.readTimeout(60, TimeUnit.SECONDS);
            client.writeTimeout(60, TimeUnit.SECONDS);

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(Utils.BASE_URl)
                        .client(client.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addConverterFactory(SimpleXmlConverterFactory.create())
                        .build();
            }
            return retrofit;

        }


}
