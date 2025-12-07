package org.uob.a2.engine;

import org.uob.a2.model.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumMap;
import java.util.Map;

public class SimulationState {

    private List<Producer> producers;
    private List<Converter> converters;
    private List<Consumer> consumers;
    private Map<ResourceType, Integer> inventory = new EnumMap<>(ResourceType.class);
    private List<Map<ResourceType, Integer>> resourceHistory = new ArrayList<>();
    private Set<ShopItem> upgrades = new HashSet<>();

    public SimulationState(){// initialise all resource values to 0, credits to 1000
        for (ResourceType resource : ResourceType.values()){
            inventory.put(resource, 0);
        }
        inventory.put(ResourceType.CREDITS, 3000);

        this.producers = new ArrayList<>();
        this.converters = new ArrayList<>();
        this.consumers = new ArrayList<>();
    }

    public void updateResource(ResourceType resource, int amount){
        inventory.put(resource, amount);
    }

    public void updateHistory(){
        resourceHistory.add(inventory);
    }

    public void addResource(ResourceType resource, int amount){
        int current = inventory.get(resource);
        inventory.put(resource, current + amount);
    }

    public boolean removeResource(ResourceType resource, int amount){
        int current = inventory.get(resource);
        if (current - amount >= 0){
            inventory.put(resource, current - amount);
            return true;
        }else{
            return false;
        }
    }

    // Shop
    public void addUpgrade(ShopItem item) {
        upgrades.add(item);
    }

    // to check
    public boolean hasUpgrade(ShopItem item) {
        return upgrades.contains(item);
    }
    

    // Getters 
    public int getResourceAmount(ResourceType resource){
        return inventory.get(resource);
    }

    public Map<ResourceType, Integer> getInventory(){
        return inventory;
    }
    public List<Producer> getProducers() {
        return producers;
    }

    public List<Converter> getConverters() {
        return converters;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public List<Map<ResourceType, Integer>> getHistory(){
        return resourceHistory;
    }

    public Set<ShopItem> getUpgrades() {
        return upgrades;
    }

    //Used for graph of single resource
    public List<Integer> getHistory(ResourceType type) {
        List<Integer> singleResourceHistory = new ArrayList<>();
        for (Map<ResourceType, Integer> snapshot : resourceHistory) {
            int amount = snapshot.getOrDefault(type, 0);
            singleResourceHistory.add(amount);
        }
        
        return singleResourceHistory;
    }
    

    // Adders
    public void addProducer(Producer producer){
        producers.add(producer);
    }

    public void addConverter(Converter converter){
        converters.add(converter);
    }

    public void addConsumer(Consumer consumer){
        consumers.add(consumer);
    }

    //Misc
    public boolean canBuild(Entity entity){
        if (this.getResourceAmount(ResourceType.CREDITS) >= entity.getCosts().get(ResourceType.CREDITS)){
            int originalPrice = entity.getCosts().get(ResourceType.CREDITS);
            this.removeResource(ResourceType.CREDITS, originalPrice);
            entity.addCost(ResourceType.CREDITS, originalPrice*10);
            return true;
        }else{
            return false;
        }
    }

    
}