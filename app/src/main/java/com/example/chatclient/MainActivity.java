package com.example.chatclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ChatClient chatClient;
    private Button sendBtn;
    private EditText inputMsg;
    private TextView nameTextView;
    private  final List<Message> messages = new ArrayList<>();
    private final RecyclerView.Adapter adapter = new MessageAdapter(this.messages);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        sendBtn = findViewById(R.id.btnSend);
        inputMsg = findViewById(R.id.textInput);

        chatClient = new ChatClient("192.168.0.107", 4000,messages);
        chatClient.connect(adapter,this);

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatClient.sendMessage(inputMsg.getText().toString());
                inputMsg.setText("");
            }
        });
    }
}