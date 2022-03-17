package edu.ics372.project1.appliancestore.business.facade;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
import edu.ics372.project1.appliancestore.business.entities.Dishwasher;
import edu.ics372.project1.appliancestore.business.entities.Dryer;
import edu.ics372.project1.appliancestore.business.entities.Furnace;
import edu.ics372.project1.appliancestore.business.entities.KitchenRange;
import edu.ics372.project1.appliancestore.business.entities.Refrigerator;
import edu.ics372.project1.appliancestore.business.entities.Washer;

/**
 * A class factory to create specific appliance objects based on type
 * or match appliances by their ID prefix.
 * @author Sharon Shin and Parker Talley
 */
public class ApplianceFactory {
    public static final int WASHER = 1;
	public static final int DRYER = 2;
	public static final int KITCHENRANGE = 3;
	public static final int REFRIGERATOR = 4;
	public static final int FURNACE = 5;
	public static final int DISHWASHER = 6;

    /**
     * This method takes in an int type and Request object to create 
     * the requested type of object with the attributes placed in the
     * request object. It returns the appliance object.
     * 
     * @param int of the type of appliance
     * @param request Object with attributes of appliance
     * @return Appliance object
     */
    public static Appliance createAppliance(int type, Request request ){
        Appliance appliance = null;
        switch(type) {
            case WASHER:  appliance = new Washer(request.getBrandName(), request.getModelName(), request.getPrice(), 
            request.getRepairPlanAmount());
            break;
            case DRYER: appliance =  new Dryer(request.getBrandName(), request.getModelName(), request.getPrice(),
            request.getRepairPlanAmount());
            break;
            case KITCHENRANGE: appliance =  new KitchenRange(request.getBrandName(), request.getModelName(), request.getPrice());
            break;
            case REFRIGERATOR: appliance =  new Refrigerator(request.getBrandName(), request.getModelName(), request.getPrice(),
            request.getCapacity());
            break;
            case FURNACE: appliance =  new Furnace(request.getBrandName(), request.getModelName(), request.getPrice(),
            request.getMaxHeatingOutput());
            break;
            case DISHWASHER: appliance =  new Dishwasher(request.getBrandName(), request.getModelName(), request.getPrice()); 
        }
        return appliance;
    }

    /**
     * This method takes in an int type and returns the associated 
     * appliance ID prefix.
     * @param int type of appliance
     * @return String ID prefix of appliance
     */
    public static String findApplianceType(int type) {
        String applianceString = null;
        switch(type) {
            case WASHER:  applianceString = "WASH";
            break;
            case DRYER: applianceString =  "DRY";
            break;
            case KITCHENRANGE: applianceString =  "KIT";
            break;
            case REFRIGERATOR: applianceString =  "REFR";
            break;
            case FURNACE: applianceString =  "FURN";
            break;
            case DISHWASHER: applianceString =  "DSHW"; 
        }
        return applianceString;
    
    }
}