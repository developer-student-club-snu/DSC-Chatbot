package com.example.dsc_chatbot.Activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dsc_chatbot.R;
import com.example.dsc_chatbot.RestAPI;

import java.util.Map;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    EditText messageBox;
    ImageView sendButton;

    private KonfettiView viewKonfetti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageBox = findViewById(R.id.messageBox);
        sendButton = findViewById(R.id.sendButton);

        viewKonfetti = findViewById(R.id.viewKonfetti);
    }

    public void onSendClick(View view) {
        String message = messageBox.getText().toString();

        if(message.toLowerCase().equals("confetti")){
            viewKonfetti.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.RECT, Shape.CIRCLE)
                    .addSizes(new Size(12, 5))
                    .setPosition(-50f, viewKonfetti.getWidth() + 50f, -50f, -50f)
                    .streamFor(300, 5000L);
        }else {

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            Log.d("SEND", "Send was clicked");
            RestAPI.getAppService(false).getReply(message).enqueue(new Callback<Map<String, String>>() {
                @Override
                public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                    Log.d("Call", "onResponse: " + response.body().get("response"));
                    Toast.makeText(ChatActivity.this, response.body().get("response"), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Map<String, String>> call, Throwable t) {
                    Log.e("Call", "onResponse: " + t.toString());
                }
            });
        }
        messageBox.setText("");

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(messageBox.getWindowToken(), 0);
    }
}
