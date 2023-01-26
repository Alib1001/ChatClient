package com.example.chatclient;

public class Message {
    private String message;
    private String name = "Улиточка";

    public Message(){

    }

    public Message(String message,String name){
        this.message = message;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public String getMessage() {
        return message;
    }


}
