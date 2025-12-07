package org.uob.a2.parser;

import org.uob.a2.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Arrays;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import java.util.Collections;


public class Parser {



    public Parser(){}
    
    // Return parsed Command object from string user input
    public Command parse(String command){
        command = command.trim().toLowerCase(); //remove extra whitespace around string, turn to lowercase
        String[] splitCmd = command.split("\\s+"); // Split into array of words by whitespace (accounts for two or more whitespaces if entered accidentally)
        List<String> words = Arrays.asList(splitCmd); // Turns array into list object
        String cmd = words.get(0);

        

        switch(cmd){
            case "build":
            case "b":
                return new BuildCommand(words);
            case "tick":
            case "t":
                return new TickCommand(words);
            case "quit":
            case "q":
                return new QuitCommand(words);
            case "cheat":
                return new CheatCommand(words);
            case "shop":
                return new ShopCommand(words);
            case "graph":
            case "g":
                return new GraphCommand(words);
            case "info":
            case "i":
                return new InfoCommand(words);
            case "help":
            case "h":
                return new HelpCommand(words);
            case "race":
            case "r":
                return new RaceCommand(words);
            case "save":
            // case "s":
                return new SaveCommand(words);
            case "load":
            case "l":
                return new LoadCommand(words);
            default:
                return new InvalidCommand(words);
                
        }

        

        
        
        
    }
    
   
}