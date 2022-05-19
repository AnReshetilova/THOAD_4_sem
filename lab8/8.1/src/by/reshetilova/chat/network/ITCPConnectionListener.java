package by.reshetilova.chat.network;

public interface ITCPConnectionListener {
    void OnConnectionReady(TCPConnection tcpConnection);
    void OnReceiveString(TCPConnection tcpConnection, String value);
    void OnDisconnect(TCPConnection tcpConnection);
    void OnException(TCPConnection tcpConnection, Exception e);
}
