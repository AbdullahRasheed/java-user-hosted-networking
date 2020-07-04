package me.abdullah.packets;

import java.io.Serializable;

public class MessageSentPacket implements Serializable {
    public int id;
    public String username;
    public String message;
}
