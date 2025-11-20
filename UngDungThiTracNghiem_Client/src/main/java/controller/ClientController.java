package controller;

import java.io.*;
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
