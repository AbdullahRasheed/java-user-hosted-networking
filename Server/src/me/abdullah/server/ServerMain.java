package me.abdullah.server;

public class ServerMain {

    public static void main(String[] args) {
//        Server server = new Server(25565);
//        server.start();
    }

    public ServerMain(int port){
        Server server = new Server(port);
        server.start();
    }
}
