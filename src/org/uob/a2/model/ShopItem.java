package org.uob.a2.model;

public enum ShopItem{

    
    //FROM SHOP- (COST, DESC, MOD)
    TWIN_TURBO(100000, "Enhances engine performance, increasing your win rate by 40%", 0.4),
    EV_ENGINE(150000,"This electric engine will thunder your car into higher placements, boosting your win rates 50%!", 0.5),
    SPOILER(70000, "Adds better aerodynamic control to your car, increasing your handling and your win rate by 15%.", 0.15),
    NITROUS(200000, "This illegal modification rockets your car to the highest speeds imaginable, increasing your win rate by 90%!", 0.9),
    LIGHT_CHASSIS(80000,"This enhancement reduces your car weight, thus increasing your speed and win rate by 35%.", 0.35),
    ADVERT(40000, "Increases your popularity and all payouts per race by 50%", 1.5);

    private final int cost;
    private final String desc;
    private final double modifier;

    ShopItem(int cost, String desc, double modifier){
        this.cost = cost;
        this.desc = desc;
        this.modifier = modifier;
    }

    public int getCost(){
        return cost;
    }

    public String getDesc() {
        return desc;
    }

    public double getMod(){
        return modifier;
    }


        
    
}