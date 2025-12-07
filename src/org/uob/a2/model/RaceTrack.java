package org.uob.a2.model;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import java.util.Random;


public class RaceTrack extends Consumer implements Tickable{

    private int level = 1;
    private boolean canRace = false;
    
    
    public RaceTrack(){
        super("Race Track", ResourceType.RACE_CAR, 1);
        this.addCost(ResourceType.CREDITS, 100);
        this.level = level;
        this.canRace = canRace;

    }

    @Override
    public void consume(Context ctx){
        if (ctx.state().getResourceAmount(this.getProduct()) > this.getAmount() - 1){// Consume car once made
            String message = this.getProduct().name() + "(" + this.getAmount() + ")" + " -> " +this.getName();
            ctx.state().removeResource(this.getProduct(), this.getAmount());
            System.out.println(message);
            System.out.println("");
            this.level++;
            System.out.println("Race Track (+1 Level)");
            this.setCanRace(true);
        }
    }

    public int getLevel(){
        return this.level;
    }

    public void tick(Context ctx){
        this.consume(ctx);
    }

    public boolean getCanRace(){
        return canRace;
    }

    public void setCanRace(boolean canRace){
        this.canRace = canRace;
    }
    
    @Override
    public String toCSV(){
        return this.getName() + "," + this.getProduct() + "," + this.getAmount() + "," + this.level + "," + Saver.MapToString(this.getCosts());
    }
}