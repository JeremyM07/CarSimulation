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
            int win = this.generator.nextInt(1000);
            double winScore = win;
            double bonus = 1;
            if (ctx.state().hasUpgrade(ShopItem.NITROUS)){
                winScore -= winScore*(ShopItem.NITROUS.getMod());
            } if (ctx.state().hasUpgrade(ShopItem.EV_ENGINE)){
                winScore -= winScore*(ShopItem.EV_ENGINE.getMod());
            }if (ctx.state().hasUpgrade(ShopItem.LIGHT_CHASSIS)){
                winScore -= winScore*(ShopItem.LIGHT_CHASSIS.getMod());
            } if (ctx.state().hasUpgrade(ShopItem.TWIN_TURBO)){
                winScore -= winScore*(ShopItem.TWIN_TURBO.getMod());
            } if (ctx.state().hasUpgrade(ShopItem.SPOILER)){
                winScore -= winScore*(ShopItem.SPOILER.getMod());
            } if (ctx.state().hasUpgrade(ShopItem.ADVERT)){
                bonus = ShopItem.ADVERT.getMod();
            }
            
            if (winScore < 10*this.level){// first place rare initially
                ctx.state().addResource(ResourceType.CREDITS, (int) (12000*bonus));
                return "Your car came 1ST! Massive payout! (+12,000 Credits)" + "\nBonus: " + ((12000*bonus)-12000);
            }else if(winScore < 20*this.level){
                ctx.state().addResource(ResourceType.CREDITS, (int) (10500*bonus));
                return "Your car came 2ND! Huge payout! (+10,500 Credits)"  + "\nBonus: " + ((10500*bonus)-10500);
            }else if (winScore < 30*this.level){
                ctx.state().addResource(ResourceType.CREDITS,(int) (9000*bonus));
                return "Your car came 3RD! Large payout! (+9,000 Credits)"  + "\nBonus: " + ((9000*bonus)-9000);
            }else if (winScore < 55*this.level){
                ctx.state().addResource(ResourceType.CREDITS, (int) (8000*bonus));
                return "Your car came 4TH! Big payout! (+8,000 Credits)"  + "\nBonus: " + ((8000*bonus)-8000);
            }else if (winScore < 70*this.level){
                ctx.state().addResource(ResourceType.CREDITS, (int) (7500*bonus));
                return "Your car came 5TH! Big payout! (+7,500 Credits)"  + "\nBonus: " + ((7500*bonus)-7500);
            }else if(winScore < 125*this.level){
                ctx.state().addResource(ResourceType.CREDITS, (int) (7000*bonus));
                return "Your car came 6TH! Big payout! (+7,000 Credits)"  + "\nBonus: " + ((7000*bonus)-7000);
            }else if(winScore < 200*this.level){
                ctx.state().addResource(ResourceType.CREDITS, (int) (6000*bonus));
                return "Your car came 7TH! Big payout! (+6,000 Credits)"  + "\nBonus: " + ((6000*bonus)-6000);
            }else if(winScore < 350*this.level){
                ctx.state().addResource(ResourceType.CREDITS, (int) (5500*bonus));
                return "Your car came 8TH! Big payout! (+5,500 Credits)"  + "\nBonus: " + ((5500*bonus)-5500);
            }else{
                ctx.state().addResource(ResourceType.CREDITS, (int) (5000*bonus));
                return "Your car came 9TH! Last place :(  Big payout! (+5,000 Credits)"  + "\nBonus: " + ((5000*bonus)-5000);
            }
        }
}
