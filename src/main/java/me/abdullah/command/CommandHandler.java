package me.abdullah.command;

import me.abdullah.main.Main;
import me.abdullah.packets.MessageSentPacket;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandHandler {

    private static Scanner sc = new Scanner(System.in);
    private static Map<String, Command> commands = new HashMap<>();

    public static void await(Main main, boolean recurs){
        String input = sc.nextLine();
        CommandHandler cmdHandler = new CommandHandler();
        if(cmdHandler.commandExists(input)) cmdHandler.executeCommand(input);
        else{
            if(main.getClient() != null){
                MessageSentPacket packet = new MessageSentPacket();
                packet.username = main.getClient().getUsername();
                packet.message = input;
                main.getClient().sendObject(packet);
            }
        }
        if(recurs) await(main, recurs);
    }

    public void addCommand(String cmd, Command command){
        commands.put(cmd, command);
    }

    public void executeCommand(String input){
        String[] args = input.split(" ");
        commands.get(args[0].toLowerCase()).onCommand(
                Arrays.copyOfRange(args, 1, args.length)
        );
    }

    public boolean commandExists(String input){
        return commands.containsKey(input.split(" ")[0].toLowerCase());
    }
}
