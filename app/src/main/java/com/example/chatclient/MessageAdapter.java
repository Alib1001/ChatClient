package com.example.chatclient;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Message> messages;

    public MessageAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.items, parent, false)
        ) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView msgView = holder.itemView.findViewById(R.id.msgTv);
        TextView nameView = holder.itemView.findViewById(R.id.nameTv);

        String regex = "/";

        String connectedStr = NameActivity.message.getName() + regex + this.messages.get(position).getMessage();
        String[] words = connectedStr.split(regex);

        try {
            msgView.setText(words[1]);
        }
        catch (ArrayIndexOutOfBoundsException e){
            msgView.setText("");
            e.printStackTrace();
        }

        try {
            nameView.setText(words[0]);
        }
        catch (ArrayIndexOutOfBoundsException e){
            nameView.setText("Улиточка");
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return this.messages.size();
    }
}
