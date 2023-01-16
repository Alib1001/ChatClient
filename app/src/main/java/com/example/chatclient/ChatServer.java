package com.example.chatclient;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

//Run this code in another IDE
public class ChatServer {
    ArrayList clientOutputStreams;


    public static void main(String[] args){
        ChatServer chatServer = new ChatServer();
        chatServer.startServer();
    }
    public class ClientHandler implements Runnable{
        BufferedReader reader;
        Socket socket;
        public ClientHandler(Socket clientSocket){
            try {
                socket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String msg = "";
                String name = "Anon";
                while ((msg = reader.readLine())!= null){
                    System.out.println("read:" + msg);
                    tellEveryOne(msg);
                }
            }
            catch (Exception e ){
            }
        }
    }

    private void tellEveryOne(String msg) {
        Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()){
            PrintWriter writer = (PrintWriter) it.next();
            writer.println(msg);
            writer.flush();
        }
    }

    public void startServer() {
        clientOutputStreams = new ArrayList();
        try {
            ServerSocket serverSocket = new ServerSocket(4000);

            while (true){
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);
                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
                System.out.println("Connected");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
