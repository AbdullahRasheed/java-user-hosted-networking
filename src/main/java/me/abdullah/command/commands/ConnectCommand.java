package me.abdullah.command.commands;

import me.abdullah.command.Command;
import me.abdullah.main.Main;
import me.abdullah.packets.AddConnectionPacket;
import me.abdullah.uhmsg.client.Client;

import java.util.regex.Pattern;

public class ConnectCommand implements Command {

    private static final String zeroTo255
            = "(\\d{1,2}|(0|1)\\"
            + "d{2}|2[0-4]\\d|25[0-5])";
    private static final String ipRegex
            = zeroTo255 + "\\."
            + zeroTo255 + "\\."
            + zeroTo255 + "\\."
            + zeroTo255;

    private Main main;
    public ConnectCommand(Main main){
        this.main = main;
    }

    @Override
    public void onCommand(String[] args) {
        if(args.length < 3){
            System.out.println("Correct usage: 'connect <username> <ip> <port>");
            return;
        }

        if(!validIp(args[1])){
            System.out.println("That is not a valid IP!");
            System.out.println("Correct usage: 'connect <username> <ip> <port>");
            return;
        }

        String username = args[0];
        String ip = args[1];
        int port;
        try{
            port = Integer.parseInt(args[2]);
        }catch (NumberFormatException e){
            System.out.println("A port must be a number!");
            System.out.println("Correct usage: 'connect <username> <ip> <port>");
            return;
        }

        if(main.getClient() != null) main.getClient().close();
        Client client = new Client(ip, port, username);
        client.connect();
        main.setClient(client);
        AddConnectionPacket packet = new AddConnectionPacket();
        packet.username = username;
        main.getClient().sendObject(packet);
    }

    private boolean validIp(String ip){
        return Pattern.compile(ipRegex).matcher(ip).matches();
    }
}
