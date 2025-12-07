package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import java.util.List;
import java.util.Random;
import org.uob.a2.model.RaceTrack;

public class RaceCommand extends Command {

    private Random generator;
    private int level;


    public RaceCommand(List<String> words) {
        super(words);
        this.generator = new Random();
        this.level = 0;
    }
    @Override
    public String execute(Context ctx) {
            if (ctx.state().getConsumers().isEmpty()){
                return "You need to build a race track first";
            }
            Consumer c = ctx.state().getConsumers().get(0);
            if (c instanceof RaceTrack) {
                RaceTrack r = (RaceTrack) c;
                this.level = r.getLevel();
            } else {
                return "No Race Track found to race on!";
            }

            RaceTrack r = (RaceTrack) c;
            if (!r.getCanRace()){// Check if car consumed
                return "Car has not been placed on (consumed by) race track yet!";
            }else{
                r.setCanRace(false);
            }
            
            System.out.println("");
            int winScore = this.generator.nextInt(1000);
            if (winScore < 15*this.level){// first place rare initially
                ctx.state().addResource(ResourceType.CREDITS, 12000);
                return "Your car came 1ST! Massive payout! (+12,000 Credits)" + "\nCredits: " + ctx.state().getResourceAmount(ResourceType.CREDITS);
            }else if(winScore < 25*this.level){
                ctx.state().addResource(ResourceType.CREDITS, 10500);
                return "Your car came 2ND! Huge payout! (+10,500 Credits)"  + "\nCredits: " + ctx.state().getResourceAmount(ResourceType.CREDITS);
            }else if (winScore < 35*this.level){
                ctx.state().addResource(ResourceType.CREDITS, 9000);
                return "Your car came 3RD! Large payout! (+9,000 Credits)"  + "\nCredits: " + ctx.state().getResourceAmount(ResourceType.CREDITS);
            }else if (winScore < 60*this.level){
                ctx.state().addResource(ResourceType.CREDITS, 8000);
                return "Your car came 4TH! Big payout! (+8,000 Credits)"  + "\nCredits: " + ctx.state().getResourceAmount(ResourceType.CREDITS);
            }else if (winScore < 75*this.level){
                ctx.state().addResource(ResourceType.CREDITS, 7500);
                return "Your car came 5TH! Big payout! (+7,500 Credits)"  + "\nCredits: " + ctx.state().getResourceAmount(ResourceType.CREDITS);
            }else if(winScore < 150*this.level){
                ctx.state().addResource(ResourceType.CREDITS, 7000);
                return "Your car came 6TH! Big payout! (+7,000 Credits)"  + "\nCredits: " + ctx.state().getResourceAmount(ResourceType.CREDITS);
            }else if(winScore < 300*this.level){
                ctx.state().addResource(ResourceType.CREDITS, 6000);
                return "Your car came 7TH! Big payout! (+6,000 Credits)"  + "\nCredits: " + ctx.state().getResourceAmount(ResourceType.CREDITS);
            }else if(winScore < 500*this.level){
                ctx.state().addResource(ResourceType.CREDITS, 5500);
                return "Your car came 8TH! Big payout! (+5,500 Credits)"  + "\nCredits: " + ctx.state().getResourceAmount(ResourceType.CREDITS);
            }else{
                ctx.state().addResource(ResourceType.CREDITS, 5000);
                return "Your car came 9TH! Last place :(  Big payout! (+5,000 Credits)"  + "\nCredits: " + ctx.state().getResourceAmount(ResourceType.CREDITS);
            }
        }
}
