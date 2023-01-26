package com.example.chatclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NameActivity extends AppCompatActivity {
    private EditText inputName;
    private TextView nameTv;
    private String EnteredName;
    private Button submitBtn;

    public static Message message = new Message();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        nameTv = findViewById(R.id.nameView);
        inputName = findViewById(R.id.nameEdit);
        submitBtn = findViewById(R.id.submitButton);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputName.getText().toString() != "") {
                    EnteredName = inputName.getText().toString();
                    nameTv.setText(EnteredName);
                    message.setName(EnteredName);
                    Intent intent = new Intent(NameActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Enter name !",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}