package com.example.dsc_chatbot.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dsc_chatbot.R;
import com.example.dsc_chatbot.RestAPI;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestAPI.getAppService(true).getHeroes().enqueue(new Callback<List<Map<String, String>>>() {
            @Override
            public void onResponse(Call<List<Map<String, String>>> call, Response<List<Map<String, String>>> response) {
                Log.d("Call", "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Map<String, String>>> call, Throwable t) {
                Log.e("Call", t.toString());
            }
        });
    }

    public void onButtonClick(View view) {
        startActivity(new Intent(this, ChatActivity.class));
    }
}
