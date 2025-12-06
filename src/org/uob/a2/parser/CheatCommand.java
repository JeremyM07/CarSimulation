package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import java.util.List;
import org.uob.a2.model.*;

public class CheatCommand extends Command {

    public CheatCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        for (ResourceType r: ctx.state().getInventory().keySet()){
            ctx.state().addResource(r, 999);
        }
        return "CHEAT CMD: +999 ALL.";
    }
}