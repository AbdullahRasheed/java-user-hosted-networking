package me.abdullah.command.commands;

import me.abdullah.command.Command;
import me.abdullah.main.Main;

public class DisconnectCommand implements Command {

    private Main main;
    public DisconnectCommand(Main main){
        this.main = main;
    }

    @Override
    public void onCommand(String[] args) {
        if(main.getClient() == null){
            System.out.println("You are not connected to a server yet");
            return;
        }

        main.getClient().close();
        main.setClient(null);
    }
}
