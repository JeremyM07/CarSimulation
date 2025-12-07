package org.uob.a2.parser;

import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import org.uob.a2.model.ShopItem;
import java.util.List;

public class ShopCommand extends Command {

    public ShopCommand(List<String> words) { 
        super(words); 
    }

    @Override
    public String execute(Context ctx) {
        if (words.size() <= 1) {
            return "Usage: 'shop list' or 'shop buy <item>'";
        }

        String action = words.get(1);

        if (action.equals("list")) {
            StringBuilder sb = new StringBuilder("--- SHOP CATALOG ---\n");
            for (ShopItem item : ShopItem.values()) {
                String status = ctx.state().hasUpgrade(item) ? " [OWNED]" : "";
                sb.append(String.format("%-18s : %d Credits %s\n   Desc: %s\n", 
                    item.name(), item.getCost(), status, item.getDesc()));
            }
            return sb.toString();
        }

        if (action.equals("buy")) {
            ShopItem item = null;
            if (words.size() < 3) return "Buy what? (e.g., shop buy nitrous)";
            
            String itemName = words.get(2).toUpperCase();
            try {
                item = ShopItem.valueOf(itemName);
            } catch (Exception e){
                System.out.println("Processing...");
            }
            if (item == null){
                if (words.get(2).equals("ev")){
                    item = ShopItem.EV_ENGINE;
                }else if (words.get(2).equals("light")){
                    item = ShopItem.LIGHT_CHASSIS;
                } else if (words.get(2).equals("twin")){
                    item = ShopItem.TWIN_TURBO;
                }else {
                    return "Not an item in the shop. Check 'help shop' for more.";
                }
            }

                // check if already own
            if (ctx.state().hasUpgrade(item)) {
                return "You already own " + item.name();
            }

            // check credits
            int currentCredits = ctx.state().getResourceAmount(ResourceType.CREDITS);
            if (currentCredits < item.getCost()) {
                return "Too expensive! You need " + item.getCost() + " Credits.";
            }

            // process transaction
            ctx.state().removeResource(ResourceType.CREDITS, item.getCost());
            ctx.state().addUpgrade(item);

            return "Successfully purchased " + item.name() + "!";

            
            }
        return "Unknown shop action.";
        }

}
