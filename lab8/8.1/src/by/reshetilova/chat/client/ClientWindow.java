package by.reshetilova.chat.client;

import by.reshetilova.chat.network.ITCPConnectionListener;
import by.reshetilova.chat.network.TCPConnection;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientWindow implements ITCPConnectionListener {
    private TCPConnection connection;
    private final int PORT = 8189;

    public static void main(String[] args) {
        new ClientWindow();
    }

    ClientWindow() {
        try {
            connection = new TCPConnection(this, InetAddress.getLocalHost(), PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String cmd = scanner.nextLine();
            connection.SendString(cmd);
        }
    }

    @Override
    public void OnConnectionReady(TCPConnection tcpConnection) {

    }

    @Override
    public void OnReceiveString(TCPConnection tcpConnection, String value) {
        System.out.println(tcpConnection + ": " + value);
    }

    @Override
    public void OnDisconnect(TCPConnection tcpConnection) {
        System.out.println("disconnected");
    }

    @Override
    public void OnException(TCPConnection tcpConnection, Exception e) {
        System.out.println("ex");
    }
}
