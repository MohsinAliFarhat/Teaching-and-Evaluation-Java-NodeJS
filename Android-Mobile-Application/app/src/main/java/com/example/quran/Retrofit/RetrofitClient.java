package com.example.quran.Retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static Retrofit instance;

    public static Retrofit getInstance() {

        if (instance == null)
        {
            instance = new Retrofit.Builder()

            //.baseUrl("http://10.113.48.1:3000")

            //10.113.48.1

                    .baseUrl("http://192.168.43.85:3000")

                   // .baseUrl("http://10.0.2.2:3000") // IN EMULATOR LOCALHOST WILL CHANGE TO 10.0.2.2
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return instance;
    }
}
