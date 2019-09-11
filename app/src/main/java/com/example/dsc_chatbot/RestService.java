package com.example.dsc_chatbot;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestService {

    String BASE_URL_MARVEL = "https://simplifiedcoding.net/demos/";
    String BASE_URL = "http://ab54a571.ngrok.io/";

    @GET("marvel")
    Call<List<Map<String, String>>> getHeroes();

    @GET("get")
    Call<Map<String, String>> getReply(@Query("msg") String msg);
}
