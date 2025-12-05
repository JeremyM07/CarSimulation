package org.uob.a2.parser;

import org.uob.a2.engine.*;
import java.util.List;
import org.uob.a2.*;

public class TickCommand extends Command {

    public TickCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        int ticks = 1;
        
        // Check if user type number
        if (words.size() > 1) {
            try {
                ticks = Integer.parseInt(words.get(1));
            } catch (Exception e) {
                return "Invalid number.";
            }
        }

        // Logic
        for (int i = 0; i < ticks; i++) {
            ctx.engine().nextTick();
        }

        return "Advanced time by " + ticks + " ticks.";
    }
}