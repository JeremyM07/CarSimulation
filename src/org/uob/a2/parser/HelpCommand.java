package org.uob.a2.parser;

import org.uob.a2.engine.*;
import java.util.List;

public class HelpCommand extends Command {

    public HelpCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        // general
        if (words.size() < 2) {
            return "Available Commands:\n" +
                   "  build <name>   : Construct a building (e.g., 'build coal mine')\n" +
                   "  race           : Final action of race track. Gain credits.\n" +
                   "  tick [n]       : Advance time by n ticks\n" +
                   "  info [topic]   : View details of an entity or resource\n" +
                   "  graph <resource>    : Plot the history of a resource\n" +
                   "  help [cmd]     : Get detailed help for a command\n" +
                   "  cheat 'build'  : 'cheat' Gives +999 resources or use 'cheat build' for all entities built.\n" +
                   "  save/load      :  Saves game to csv file or loads existing game from csv file.\n" + 
                   "  quit           : Exit the simulation";
        }

        // specific
        
        String topic = words.get(1);

        switch (topic) {
            case "build":
                return "--- Build Command ---\n" +
                       "Usage: build|b <entity_name>\n" +
                       "Description: Constructs a new building in the simulation.\n" +
                       "Requirements: You must have enough CREDITS.\n" +
                       "Examples: 'build iron', 'b smelter', 'build race_academy'.";

            case "tick":
                return "--- Tick Command ---\n" +
                       "Usage: tick|t [number]\n" +
                       "Description: Advances the simulation time. Default is 1 tick.\n" +
                       "During a tick:\n" +
                       "  - Producers create raw materials.\n" +
                       "  - Converters consume inputs and create outputs.\n" +
                       "  - Consumers eat final resource (Race Car).\n" +
                       "  - Use command 'race' for final race track action";

            case "info":
                return "--- Info Command ---\n" +
                       "Usage: info|i [topic]\n" +
                       "Description: Displays stats for a resource or blueprint details for a building.\n" +
                       "Examples:\n" +
                       "  'info'          -> Show full inventory and credits\n" +
                       "  'i iron'     -> Show current iron count\n" +
                       "  'info smelter'  -> Show smelter recipe, cost, and description";

            case "graph":
                return "--- Graph Command ---\n" +
                       "Usage: graph|g <resource_name>\n" +
                       "Description: Draws a text-based history graph of the specified resource.\n" +
                       "Example: 'graph steel'\n" +
                       "Note: Requires 'tick' to have run at least once to have history data.";

            case "quit":
            case "exit":
                return "--- Quit Command ---\n" +
                       "Usage: quit|q\n" +
                       "Description: Safely exits the game loop and ends the program.";
            case "race":
                return "--- Race Command ---\n" +
                       "Usage: race\n" +
                       "Description: Races your built car on a race track for credits!";

            case "help":
                return "--- Help Command ---\n" +
                       "Usage: help|h [command]\n" +
                       "Description: Shows detailed usage instructions for a specific command.";
            case "shop":
                return "--- Shop Command ---\n" +
                       "Usage: shop [item]\n" +
                       "Description: Shop for modifications to boost win rate or bonus payout.";

            default:
                return "Unknown command: '" + topic + "'. Type 'help' for a list of valid commands.";
        }
    }
}