package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import java.util.List;

public class BuildCommand extends Command{

    public BuildCommand(List<String> words){
        super(words);
    }

    public String execute(Context ctx){
        if (words.size() < 2){
            return "Build what? Type build|b <entity>.";
        }

        else if (words.size() > 3){
            return "Too many words for a command. Type build|b <entity>. Entity will be 2 words max. Check 'help commands'. ";
        }
        
        else{// build entities
            
            String entity = words.get(1);

            switch(entity){
                // Producers
                case "beach":
                    Beach beach = new Beach();
                    if (ctx.state().canBuild(beach)){
                        ctx.state().addProducer(beach);
                        return "Built a Beach.";
                    }else{
                        return "Not enough credits to build a beach.";
                    }
                case "university":
                case "uni":
                    University university = new University();
                    if (ctx.state().canBuild(university)){
                        ctx.state().addProducer(university);
                        return "Built a University.";
                    }else{
                        return "Not enough credits to build a university.";
                    }
                case "reservoir":
                case "oil":
                    OilReservoir oil = new OilReservoir();
                    if (ctx.state().canBuild(oil)){
                        ctx.state().addProducer(oil);
                        return "Built an Oil Reservoir.";
                    }else{
                        return "Not enough credits to build an oil Reservoir.";
                    } 
                case "coal":
                    CoalMine coal = new CoalMine();
                    if (ctx.state().canBuild(coal)){
                        ctx.state().addProducer(coal);
                        return "Built a Coal Mine.";
                    }else{
                        return "Not enough credits to build a Coal Mine.";
                    }

                case "copper":
                    CopperMine copper = new CopperMine();
                    if (ctx.state().canBuild(copper)){
                        ctx.state().addProducer(copper);
                        return "Built a Copper Mine.";
                    }else{
                        return "Not enough credits to build a Copper Mine.";
                    }
                case "iron":
                    IronMine iron = new IronMine();
                    if (ctx.state().canBuild(iron)){
                        ctx.state().addProducer(iron);
                        return "Built an Iron Mine.";
                    }else{
                        return "Not enough credits to build an Iron Mine.";
                    }

                 //converters   
                case "car":
                    CarAssembler carassemble = new CarAssembler();
                    if (ctx.state().canBuild(carassemble)){
                        ctx.state().addConverter(carassemble);
                        return "Built a Car Assembler.";
                    }else{
                        return "Not enough credits to build a Car Assembler.";
                    }
                case "chassis":
                    ChassisFactory chassis = new ChassisFactory();
                    if (ctx.state().canBuild(chassis)){
                        ctx.state().addConverter(chassis);
                        return "Built a Chassis Factory.";
                    }else{
                        return "Not enough credits to build a Chassis Factory.";
                    }
                case "chemical":
                case "chem":
                    ChemicalPlant chem = new ChemicalPlant();
                    if (ctx.state().canBuild(chem)){
                        ctx.state().addConverter(chem);
                        return "Built a Chemical Plant.";
                    }else{
                        return "Not enough credits to build a Chemical Plant.";
                    }
                case "dashboard":
                case "dash":
                    DashboardFactory dashboard = new DashboardFactory();
                    if (ctx.state().canBuild(dashboard)){
                        ctx.state().addConverter(dashboard);
                        return "Built a Dashboard Factory.";
                    }else{
                        return "Not enough credits to build a Dashboard Factory.";
                    }
                case "engine":
                    EngineFactory enginefactory = new EngineFactory();
                    if (ctx.state().canBuild(enginefactory)){
                        ctx.state().addConverter(enginefactory);
                        return "Built a Engine Factory.";
                    }else{
                        return "Not enough credits to build a Engine Factory.";
                    }
                case "extruder":
                    Extruder extruder = new Extruder();
                    if (ctx.state().canBuild(extruder)){
                        ctx.state().addConverter(extruder);
                        return "Built an Extruder.";
                    }else{
                        return "Not enough credits to build a Extruder.";
                    }
                case "furnace":
                    Furnace furnace = new Furnace();
                    if (ctx.state().canBuild(furnace)){
                        ctx.state().addConverter(furnace);
                        return "Built a Furnace.";
                    }else{
                        return "Not enough credits to build a Furnace.";
                    }
                case "race":
                    if (words.size() > 2){
                        if (words.get(2) == "academy"){
                            RaceAcademy raceacademy = new RaceAcademy();
                            if (ctx.state().canBuild(raceacademy)){
                                ctx.state().addConverter(raceacademy);
                                return "Built a Race Academy.";
                            }else{
                                return "Not enough credits to build a Race Academy.";
                            }
                        }
                    }else{
                        RaceTrack racetrack = new RaceTrack();
                        if (ctx.state().canBuild(racetrack)){
                            ctx.state().addConsumer(racetrack);
                            return "Built a Race Track.";
                        }else{
                            return "Not enough credits to build a Race Track.";
                        }
                    }
                case "refinery":
                    Refinery refinery = new Refinery();
                    if (ctx.state().canBuild(refinery)){
                            ctx.state().addConverter(refinery);
                            return "Built a Refinery.";
                        }else{
                            return "Not enough credits to build a Refinery.";
                        }
                case "seat":
                    SeatFactory seat = new SeatFactory();
                    if (ctx.state().canBuild(seat)){
                            ctx.state().addConverter(seat);
                            return "Built a Seat Factory.";
                        }else{
                            return "Not enough credits to build a Seat Factory.";
                        }
                case "smelter":
                    Smelter smelter = new Smelter();
                    if (ctx.state().canBuild(smelter)){
                            ctx.state().addConverter(smelter);
                            return "Built a Smelter.";
                        }else{
                            return "Not enough credits to build a Smelter.";
                        }
                case "wheel":
                    WheelFactory wheel = new WheelFactory();
                    if (ctx.state().canBuild(wheel)){
                            ctx.state().addConverter(wheel);
                            return "Built a Wheel.";
                        }else{
                            return "Not enough credits to build a Wheel Factory.";
                        }
                case "window":
                    WindowFactory window = new WindowFactory();
                    if (ctx.state().canBuild(window)){
                            ctx.state().addConverter(window);
                            return "Built a Window Factory.";
                        }else{
                            return "Not enough credits to build a Window Factory.";
                        }
                default:
                    return "Please write a valid entity. Check help for more.";
            }
            
        }
    }


    
}