package me.abdullah.server;

import me.abdullah.packets.AddConnectionPacket;
import me.abdullah.packets.MessageSentPacket;
import me.abdullah.packets.RemoveConnectionPacket;

public class EventListener {

    public void received(Object p,Connection connection) {
        if(p instanceof AddConnectionPacket){
            AddConnectionPacket packet = (AddConnectionPacket)p;
            packet.id = connection.id;
            for (Connection c : ConnectionHandler.connections.values()) {
                c.sendObject(packet);
            }
        }
        else if (p instanceof RemoveConnectionPacket){
            RemoveConnectionPacket packet = (RemoveConnectionPacket)p;
            packet.id = connection.id;
            for (Connection c : ConnectionHandler.connections.values()) {
                c.sendObject(packet);
            }
        }
        else if (p instanceof MessageSentPacket){
            MessageSentPacket packet = (MessageSentPacket)p;
            packet.id = connection.id;
            for (Connection c : ConnectionHandler.connections.values()) {
                c.sendObject(packet);
            }
        }
    }

}
