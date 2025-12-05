package org.uob.a2.parser;

import org.uob.a2.engine.*;
import java.util.List;
import org.uob.a2.*;

public class InvalidCommand extends Command {

    public InvalidCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        return "Command not valid. Refer to help for more.";
    }
}