package org.uob.a2.model;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import org.uob.a2.data.Saver;

public class Petrol extends Consumer implements Tickable {
    
    private int numOfConsumes = 0;
    private final int maxConsumes = 1;
    
    public Petrol() {
        super("Petrol",ResourceType.ENGINEER, 1);
        this.addCost(ResourceType.CRUDE_OIL, 20);
        this.numOfConsumes = numOfConsumes;
    }

    public void tick(Context ctx){
        this.consume(ctx);
    }

    public void incrementConsumes(){
        this.numOfConsumes++;
    }

    public void setConsumes(int consumes){
        this.numOfConsumes = consumes;
    }

    public int getConsumes(){
        return this.numOfConsumes;
    }

    public int getMaxConsumes(){
        return maxConsumes;
    }

    @Override
    public void consume(Context ctx){
        if (ctx.state().getResource(this.getProduct()) >= this.getAmount() && this.getConsumes() < this.getMaxConsumes()){// One Petrol item per car made/levelled up
            String message = this.getProduct().name()+"("+this.getAmount()+")" + " + " + ResourceType.CRUDE_OIL.name() + "("+this.getCosts().get(ResourceType.CRUDE_OIL)+")" + " -> " + this.getName();
            System.out.println(message);// Consumption message for user
            ctx.state().removeResource(this.getProduct(), this.getAmount());
            this.incrementConsumes();
        }
    }

    @Override
    public String toCSV(){
        return this.getName() + "," + this.getProduct() + "," + this.getAmount() + "," + this.getConsumes() + "," + this.getMaxConsumes() +","+ Saver.MapToString(this.getCosts());
    }
}