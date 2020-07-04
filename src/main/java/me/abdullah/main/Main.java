package me.abdullah.main;

import me.abdullah.command.CommandHandler;
import me.abdullah.command.commands.ConnectCommand;
import me.abdullah.command.commands.DisconnectCommand;
import me.abdullah.command.commands.HostCommand;
import me.abdullah.command.commands.MyIpCommand;
import me.abdullah.uhmsg.client.Client;

public class Main {

    private Client client = null;

    public Main(){
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.addCommand("connect", new ConnectCommand(this));
        commandHandler.addCommand("host", new HostCommand(this));
        commandHandler.addCommand("myip", new MyIpCommand());
        commandHandler.addCommand("disconnect", new DisconnectCommand(this));

        commandHandler.await(this, true);
    }

    public static void main(String[] args){
        new Main();
    }

    public Client getClient(){
        return client;
    }

    public void setClient(Client client){
        this.client = client;
    }
}
