package org.uob.a2.parser;

import org.uob.a2.*;

import java.util.List;
import java.util.Arrays;


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
            default:
                return new InvalidCommand(words);
                
        }

        

        
        
        
    }
    
   
}