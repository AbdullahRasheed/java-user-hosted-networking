package me.abdullah.uhmsg.client;

import me.abdullah.packets.AddConnectionPacket;
import me.abdullah.packets.MessageSentPacket;
import me.abdullah.packets.RemoveConnectionPacket;

public class ClientEventListener {

    /**
     * client = new Client(ip, 25565, handler);
     *         client.connect();
     *
     *         AddConnectionPacket packet = new AddConnectionPacket();
     *         client.sendObject(packet);
     */

    public void received(Object p) {
        if(p instanceof AddConnectionPacket){
            AddConnectionPacket packet = (AddConnectionPacket)p;
            System.out.println("---" + packet.username + " has connected---");
        }
        else if(p instanceof RemoveConnectionPacket){
            RemoveConnectionPacket packet = (RemoveConnectionPacket)p;
            System.out.println("---" + packet.username + " has disconnected---");
        }
        else if(p instanceof MessageSentPacket){
            MessageSentPacket packet = (MessageSentPacket) p;
            System.out.println(packet.username + ": " + packet.message);
        }
    }
}
