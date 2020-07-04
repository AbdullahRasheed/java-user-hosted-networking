package me.abdullah.command.commands;

import com.dosse.upnp.UPnP;
import me.abdullah.command.Command;

public class MyIpCommand implements Command {

    @Override
    public void onCommand(String[] args) {
        System.out.println("Fetching...");
        System.out.println("Local IP: " + UPnP.getLocalIP());
        System.out.println("External IP: " + UPnP.getExternalIP());
    }
}
