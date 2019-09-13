package com.example.dsc_chatbot;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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
    KonfettiView viewKonfetti;

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageBox = findViewById(R.id.messageBox);
        sendButton = findViewById(R.id.sendButton);
        viewKonfetti = findViewById(R.id.viewKonfetti);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add("xgdfbdghbfdbdbdf");
        messageList.add("xgdfbdghbfdbdbdf");
        messageList.add("xgdfbdghbfdbdbdf");
        messageList.add("xgdfbdghbfdbdbdf");
        messageList.add("xgdfbdghbfdbdbdf");
        messageList.add("xgdfbdghbfdbdbdf");
        messageList.add("xgdfbdghbfdbdbdf");
        messageList.add("xgdfbdghbfdbdbdf");
        messageList.add("xgdfbdghbfdbdbdf");
        mAdapter = new MyAdapter(messageList);
        recyclerView.setAdapter(mAdapter);
    }

    public void onSendPress(View view) {
        String message = messageBox.getText().toString();
        mAdapter.addMessage(message);

        RestAPI.getAppService().getReply(message).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                  mAdapter.addMessage(response.body().get("response"));
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                Toast.makeText(ChatActivity.this, "Bad Network!", Toast.LENGTH_SHORT).show();
            }
        });


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
        }else{
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
