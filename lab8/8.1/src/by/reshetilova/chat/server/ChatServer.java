package by.reshetilova.chat.server;

import by.reshetilova.chat.network.ITCPConnectionListener;
import by.reshetilova.chat.network.TCPConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ChatServer implements ITCPConnectionListener {

    public static void main(String[] args) {
        new ChatServer();
    }

    private final ArrayList<TCPConnection> connections = new ArrayList<>();

    private ChatServer() {
        try(ServerSocket serverSocket = new ServerSocket(8189)) {
            while(true) {
                try {
                    new TCPConnection(this, serverSocket.accept());
                } catch (IOException e) {
                    System.out.println("TCPConnection exception: " + e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void OnConnectionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        SendAllConnections("Client connected: " + tcpConnection);
    }

    @Override
    public synchronized void OnReceiveString(TCPConnection tcpConnection, String value) {
        SendAllConnections(value);
    }

    @Override
    public synchronized void OnDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        SendAllConnections("Client disconnected: " + tcpConnection);
    }

    @Override
    public synchronized void OnException(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCPConnection exception: " + e);
    }

    private void SendAllConnections(String value) {
        System.out.println(value);
        for (int i = 0; i < connections.size(); i++) {
            connections.get(i).SendString(value);
        }
    }
}
