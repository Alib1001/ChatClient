package com.example.chatclient;

public class Message {
    private String message;
    private String name;

    public String getName() {
        return name;
    }
    public Message(String message,String name){
        this.message = message;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
