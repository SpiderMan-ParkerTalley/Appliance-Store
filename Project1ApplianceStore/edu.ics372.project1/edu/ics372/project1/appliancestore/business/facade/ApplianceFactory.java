package edu.ics372.project1.appliancestore.business.facade;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
import edu.ics372.project1.appliancestore.business.entities.Dishwasher;
import edu.ics372.project1.appliancestore.business.entities.Dryer;
import edu.ics372.project1.appliancestore.business.entities.Furnace;
import edu.ics372.project1.appliancestore.business.entities.KitchenRange;
import edu.ics372.project1.appliancestore.business.entities.Refrigerator;
import edu.ics372.project1.appliancestore.business.entities.Washer;

public class ApplianceFactory {
    public static final int WASHER = 1;
	public static final int DRYER = 2;
	public static final int KITCHENRANGE = 3;
	public static final int REFRIGERATOR = 4;
	public static final int FURNACE = 5;
	public static final int DISHWASHER = 6;

    public static Appliance createAppliance(int type, Request request ){
        Appliance appliance = null;
        switch(type) {
            case WASHER:  appliance = new Washer(request.getBrandName(), request.getModelName(), request.getPrice(), 0, 
            request.getRepairPlanAmount());
            break;
            case DRYER: appliance =  new Dryer(request.getBrandName(), request.getModelName(), request.getPrice(), 0, 
            request.getRepairPlanAmount());
            break;
            case KITCHENRANGE: appliance =  new KitchenRange(request.getBrandName(), request.getModelName(), request.getPrice(), 0);
            break;
            case REFRIGERATOR: appliance =  new Refrigerator(request.getBrandName(), request.getModelName(), request.getPrice(), 0, 
            request.getCapacity());
            break;
            case FURNACE: appliance =  new Furnace(request.getBrandName(), request.getModelName(), request.getPrice(), 0, 
            request.getMaxheatingOutput());
            break;
            case DISHWASHER: appliance =  new Dishwasher(request.getBrandName(), request.getModelName(), request.getPrice(), 0); 
        }
        return appliance;
    }

    public static Appliance findApplianceType(int type) {
        Appliance appliance = null;
        switch(type) {
            case WASHER:  appliance = new Washer("", "", 0, 0, 0);
            break;
            case DRYER: appliance =  new Dryer("", "", 0, 0, 0);
            break;
            case KITCHENRANGE: appliance =  new KitchenRange("", "", 0, 0);
            break;
            case REFRIGERATOR: appliance =  new Refrigerator("", "", 0, 0, 0);
            break;
            case FURNACE: appliance =  new Furnace("", "", 0, 0, 0);
            break;
            case DISHWASHER: appliance =  new Dishwasher("", "", 0, 0); 
        }
        return appliance;
    
    }
}