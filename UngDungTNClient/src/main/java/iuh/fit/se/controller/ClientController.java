package iuh.fit.se.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientController(String host, int port) throws IOException {
        socket = new Socket(host, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public void send(String msg) {
        writer.println(msg);
    }

    public String receive() throws IOException {
        return reader.readLine();
    }

    public void close() throws IOException {
        socket.close();
    }
}
