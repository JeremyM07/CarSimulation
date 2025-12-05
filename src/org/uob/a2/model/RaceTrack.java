package org.uob.a2.model;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import java.util.Random;


public class RaceTrack extends Consumer implements Tickable{

    private int level = 1;
    private Random generator;
    
    
    public RaceTrack(){
        super("Race Track", ResourceType.RACE_CAR, 1);
        this.addCost(ResourceType.CREDITS, 100);
        this.level = level;
        this.generator = new Random();
    }

    @Override
    public void consume(Context ctx){
        if (ctx.state().getResource(this.getProduct()) > this.getAmount() - 1){// Consume car once made
            String message = this.getProduct().name() + "(" + this.getAmount() + ")" + " -> " +this.getName();
            ctx.state().removeResource(this.getProduct(), this.getAmount());
        
            System.out.println(message);
            System.out.println("");
            int winScore = this.generator.nextInt(1000);
            if (winScore < 15*this.level){// first place rare initially
                ctx.state().addResource(ResourceType.CREDITS, 12000);
                System.out.println("Your car came 1ST! Massive payout! (+12,000 Credits)");
            }else if(winScore < 25*this.level){
                ctx.state().addResource(ResourceType.CREDITS, 10500);
                System.out.println("Your car came 2ND! Huge payout! (+10,500 Credits)");
            }else if (winScore < 35*this.level){
                ctx.state().addResource(ResourceType.CREDITS, 9000);
                System.out.println("Your car came 3RD! Large payout! (+9,000 Credits)");
            }else if (winScore < 60*this.level){
                ctx.state().addResource(ResourceType.CREDITS, 8000);
                System.out.println("Your car came 4TH! Big payout! (+8,000 Credits)");
            }else if (winScore < 75*this.level){
                ctx.state().addResource(ResourceType.CREDITS, 7500);
                System.out.println("Your car came 5TH! Big payout! (+7,500 Credits)");
            }else if(winScore < 150*this.level){
                ctx.state().addResource(ResourceType.CREDITS, 7000);
                System.out.println("Your car came 6TH! Big payout! (+7,000 Credits)");
            }else if(winScore < 300*this.level){
                ctx.state().addResource(ResourceType.CREDITS, 6000);
                System.out.println("Your car came 7TH! Big payout! (+6,000 Credits)");
            }else if(winScore < 500*this.level){
                ctx.state().addResource(ResourceType.CREDITS, 5500);
                System.out.println("Your car came 8TH! Big payout! (+5,500 Credits)");
            }else{
                ctx.state().addResource(ResourceType.CREDITS, 5000);
                System.out.println("Your car came 9TH! Last place :(  Big payout! (+5,000 Credits)");
            }
            System.out.println("");
            this.level++;
            System.out.println("Race Track (+1 Level)");
            System.out.println("CREDITS: " + ctx.state().getResource(ResourceType.CREDITS));
        }
    }

    public void tick(Context ctx){
        this.consume(ctx);
    }
    
    @Override
    public String toCSV(){
        return this.getName() + "," + this.getProduct() + "," + this.getAmount() + "," + this.level + "," + Saver.MapToString(this.getCosts());
    }
}