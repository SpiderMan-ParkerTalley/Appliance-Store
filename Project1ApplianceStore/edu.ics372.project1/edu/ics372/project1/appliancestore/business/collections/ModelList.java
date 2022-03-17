package edu.ics372.project1.appliancestore.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.project1.appliancestore.business.entities.Appliance;

/**
 * This class is a singleton that keeps track of appliances.
 * It is iterable using a safe iterator and is serializable for saving and 
 * retrieving.
 * @author Emmanuel ojogwu
 */ 


public class ModelList implements Iterable<Appliance>, Serializable {
    /**
     * For serialization/de-serialization of the data.
     */
    private static final long serialVersionUID = 1L;

    /**
     *  A list for storing Appliances. 
     */
	private List<Appliance> models = new LinkedList<Appliance>();

    /**
     * Stores the singleton of ModelList.
     */
	private static ModelList modelList;

    /**
     * This is an empty constructor that won't create an instance of this class for the purpose of singleton
     * except through the getInstance method
     */
	private ModelList() {
	}

    /**
     * Retrieves a singleton of BackOrderList.
     * @return ModelList singleton of ModelList.
     */
	public static ModelList getInstance() {
		if (modelList == null) {
			modelList = new ModelList();
		}
		return modelList;
	}

    /**This method removes all the appliance in the models list
     * @param null
     * @return void
    /**
     * This method clears the list of all models/appliances. to make an empty 
     * list.
     */
    public void clear() {
        models.clear();
    }

    /**
     * Check whether an appliance with a given appliance id exists.
     * 
     * @param applianceId String the id of the appliance
     * @return Appliance appliance object if found, null otherwise.
     */
    public Appliance search(String applianceId) {
        for (Iterator<Appliance> iterator = models.iterator(); iterator.hasNext();) {
            Appliance appliance = iterator.next();
            if(appliance.getId().equals(applianceId)) {
                return appliance;
            }
        }
        return null;
    }

    /**
     * Inserts an appliance into the collection.
     * 
     * @param appliance Appliance the appliance to be inserted.
     * @return boolean true if the appliance could be inserted. false otherwise.
     */
    public boolean insertModel(Appliance appliance) {
		models.add(appliance);
		return true;
	}

    /**
     * This method iterates through the models list
     * @param null
     * @return Iterator of type Appliance
     */
    public Iterator<Appliance> getModels() {
        return models.iterator();
    }

    /**
     * This method ruturns the models in the singleton class 
     * @param null
     * @return List of type Appliance
     */
    public List<Appliance> getModelList() {
        return models;
    }    
    
    public boolean removeModel(Appliance appliance) {
        models.remove(appliance);
        return true;
    }

    /**
     * The iterator method from the implemented class is overwritten 
     */
    @Override
    public Iterator<Appliance> iterator() {
        return models.iterator();
    }
}