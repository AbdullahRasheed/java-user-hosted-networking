package me.abdullah.command.commands;

import com.dosse.upnp.UPnP;
import me.abdullah.command.Command;
import me.abdullah.main.Main;
import me.abdullah.packets.AddConnectionPacket;
import me.abdullah.server.ServerMain;
import me.abdullah.uhmsg.client.Client;

public class HostCommand implements Command {

    private Main main;
    public HostCommand(Main main){
        this.main = main;
    }

    @Override
    public void onCommand(String[] args) {
        if(args.length < 2){
            System.out.println("Correct usage: 'host <username> <port>'");
            return;
        }

        String username = args[0];
        int port;
        try{
            port = Integer.parseInt(args[1]);
        }catch (NumberFormatException e){
            System.out.println("Port must be a number!");
            System.out.println("Correct usage: 'host <port>'");
            return;
        }

        if(port > 65535){
            System.out.println("Port must be 0-65535");
            return;
        }

        try{
            System.out.println("Setting up your server...");
            UPnP.openPortTCP(port);
        }catch (Exception e){
            System.out.println("There was a problem setting up your server");
            return;
        }

        new ServerMain(port);

        if(main.getClient() != null) main.getClient().close();
        Client client = new Client(UPnP.getLocalIP(), port, username);
        client.connect();
        main.setClient(client);
        AddConnectionPacket packet = new AddConnectionPacket();
        packet.username = username;
        main.getClient().sendObject(packet);
        System.out.println("Success!");
    }
}
