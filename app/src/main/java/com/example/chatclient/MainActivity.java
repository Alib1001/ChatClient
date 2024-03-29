package com.example.chatclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ChatClient chatClient;
    private Button sendBtn;
    private EditText inputMsg;


    private  final List<Message> messages = new ArrayList<>();
    private final RecyclerView.Adapter adapter = new MessageAdapter(this.messages);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nickname, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nickname_menu:
                Intent intent = new Intent(MainActivity.this, NameActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        sendBtn = findViewById(R.id.btnSend);
        inputMsg = findViewById(R.id.textInput);

        chatClient = new ChatClient("10.0.2.2", 4000,messages);
        chatClient.connect(adapter,this);

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputMsg.getText().toString()!="" && inputMsg.getText().toString() != null) {
                    chatClient.sendMessage(NameActivity.message.getName() +"/"+ inputMsg.getText().toString());
                }
                else {
                    Toast.makeText(getApplicationContext(),"Enter a message !",Toast.LENGTH_SHORT).show();
                }
                inputMsg.setText("");
            }
        });
    }
}
