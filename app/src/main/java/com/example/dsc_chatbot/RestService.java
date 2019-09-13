package com.example.dsc_chatbot;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestService {
    String BASE_URL = "http://13.232.205.55/";

    @GET("get")
    Call<Map<String, String>> getReply(@Query("msg") String input);
}
