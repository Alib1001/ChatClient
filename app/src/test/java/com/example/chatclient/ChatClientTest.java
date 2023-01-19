package com.example.chatclient;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class ChatClientTest {
    private ChatClient client;
    private List<Message> messages;
    private RecyclerView.Adapter adapter;
    private Activity activity;

    @Before
    public void setUp() {
        messages = new ArrayList<>();
        adapter = mock(RecyclerView.Adapter.class);
        activity = mock(Activity.class);
        client = new ChatClient("192.168.0.107", 4000, messages);
    }

    @Test
    public void testConnect() {
        client.connect(adapter, activity);
        assertTrue(client.socket.isConnected());
    }



    @Test
    public void testIncomingReader() throws InterruptedException {
        String message = "Hello World";
        CountDownLatch latch = new CountDownLatch(1);
        client.connect(adapter, activity, latch);
        client.sendMessage(message);
        latch.await();
        assertEquals(1, messages.size());
        assertEquals(message, messages.get(0).getMessage());
    }
}