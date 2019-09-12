package com.example.dsc_chatbot;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAPI {
    //Creating a retrofit object
    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RestService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
            .build();

    //creating the api interface
    static RestService api = retrofit.create(RestService.class);

    public static RestService getAppService(){
        return api;
    }
}
