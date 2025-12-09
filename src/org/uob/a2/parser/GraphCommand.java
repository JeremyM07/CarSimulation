package org.uob.a2.parser;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;
import java.util.Collections;
import java.util.List;

public class GraphCommand extends Command {

    public GraphCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        // validation
        if (words.size() < 2) {
            return "Usage: graph <resource_name> (e.g., graph iron)";
        }

        String resourceName = words.get(1);
        ResourceType type;

        
        switch (resourceName) {
            // RESOURCES
            case "iron":
            case "iron_ore":
                type = ResourceType.IRON_ORE;
                break;
            case "copper":
                if (words.get(2).equals("ore")){
                    type = ResourceType.COPPER_ORE;
                }else if (words.get(2).equals("wire")){
                    type = ResourceType.COPPER_WIRE;
                }else{
                    return "Copper wire or copper ore? Please specify.";
                }
            case "copper_ore":
                type = ResourceType.COPPER_ORE;
                break;
            case "coal":
            case "coal_ore":
                type = ResourceType.COAL_ORE;
                break;
            case "oil":
            case "crude":
            case "crude_oil":
                type = ResourceType.CRUDE_OIL;
                break;
            case "sand":
                type = ResourceType.SAND;
                break;
            case "engineer":
                type = ResourceType.ENGINEER;
                break;

            // CONVERTERS
            case "steel":
                type = ResourceType.STEEL;
                break;
            case "plastic":
                type = ResourceType.PLASTIC;
                break;
            case "rubber":
                type = ResourceType.RUBBER;
                break;
            case "glass":
                type = ResourceType.GLASS;
                break;
            case "wire":
            case "copper_wire":
                type = ResourceType.COPPER_WIRE;
                break;
            case "driver":
                type = ResourceType.DRIVER;
                break;

            // FINAL PARTS
            case "window":
                type = ResourceType.WINDOW;
                break;
            case "chassis":
                type = ResourceType.CHASSIS;
                break;
            case "engine":
                type = ResourceType.ENGINE;
                break;
            case "petrol":
                type = ResourceType.PETROL;
                break;
            case "dash":
            case "dashboard":
                type = ResourceType.DASHBOARD;
                break;
            case "wheel":
                type = ResourceType.WHEEL;
                break;
            case "seat":
                type = ResourceType.SEAT;
                break;

            // FINAL PRODUCT
            case "race":
            case "car":
                type = ResourceType.RACE_CAR;
                break;
            // MONEY
            case "credits":
            case "money":
            case "cash":
                type = ResourceType.CREDITS;
                break;

            default:
                return "Unknown resource: " + resourceName;
        }

        // data
        List<Integer> data = ctx.state().getHistory(type);
        if (data == null || data.isEmpty()) {
            return "No history data available yet. Try using 'tick' first.";
        }

        // scale
        int maxVal = Collections.max(data);
        if (maxVal == 0) maxVal = 1; // Prevent divide by zero
        int maxBarWidth = 50;

        StringBuilder sb = new StringBuilder();
        sb.append("--- History of ").append(type).append(" ---\n");
        sb.append("Peak Value: ").append(maxVal).append("\n");

        // graph
        int tick = 0;
        for (int value : data) {
            sb.append(String.format("T%-3d | ", tick++)); // ensure graph is straight

            // Calculate how many # to print
            // Logic: (CurrentValue / MaxValue) * 50
            int numStars = (int) (((double) value / maxVal) * maxBarWidth);

            // Append hashtags
            for (int i = 0; i < numStars; i++) {
                sb.append("#");
            }
            
            // Append actual number
            sb.append(" (").append(value).append(")\n");
        }

        return sb.toString();
    }
}