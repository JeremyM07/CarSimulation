package org.uob.a2.engine;

import org.uob.a2.model.*;
import java.util.List;
import java.util.ArrayList;

import java.util.EnumMap;
import java.util.Map;

public class SimulationState {

    private List<Producer> producers;
    private List<Converter> converters;
    private List<Consumer> consumers;
    private Map<ResourceType, Integer> inventory = new EnumMap<>(ResourceType.class);
    private List<Map<ResourceType, Integer>> resourceHistory = new ArrayList<>();

    public SimulationState(){// initialise all resource values to 0, credits to 1000
        for (ResourceType resource : ResourceType.values()){
            inventory.put(resource, 0);
        }
        inventory.put(ResourceType.CREDITS, 1000);

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

    // Getters 
    public int getResource(ResourceType resource){
        return inventory.get(resource);
    }

    public Map getInventory(){
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

    
    
}