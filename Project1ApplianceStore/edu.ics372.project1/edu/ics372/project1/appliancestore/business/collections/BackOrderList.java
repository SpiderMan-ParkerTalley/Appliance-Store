package edu.ics372.project1.appliancestore.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.project1.appliancestore.business.entities.BackOrder;

/**
 * This class is a singleton that keeps track of back orders of appliances.
 * It is iterable using a safe iterator and is serializable for saving and 
 * retrieving.
 * @author Parker Talley
 */
public class BackOrderList implements Iterable<BackOrder>, Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * A list got storing BackOrder(s).
     */
    private List<BackOrder> backOrders = new LinkedList<BackOrder>();
    
    /**
     * Stores the singleton of BackOrderList.
     */
    private static BackOrderList backOrderList;

    private BackOrderList() {
    }

    /**
     * Retrieves a singleton of BackOrderList.
     * @return BackOrderList singleton of BackOrderList.
     */
    public static BackOrderList getInstance() {
        if (backOrderList == null) {
            backOrderList = new BackOrderList();
        }
        return backOrderList;
    }

    /**
     * This method clears the list of back orders to make an empty list.
     */
    public void clear() {
        backOrders.clear();
    }

    /**
     * Check whether a back order with a given back order id exists.
     * @param backOrderId String the id of the back order.
     * @return BackOrder back order if found, null otherwise.
     */
    public BackOrder search(String backOrderId) {
        for (Iterator<BackOrder> iterator = backOrders.iterator(); iterator.hasNext();) {
            BackOrder backOrder = iterator.next();
            if(backOrder.getId().equals(backOrderId)) {
                return backOrder;
            }
        }
        return null;
    }

    /**
     * Inserts a back order into the collection.
     * 
     * @param backOrder BackOrder the back order to be inserted.
     * @return boolean true if the back order could be inserted.
     */
    public boolean insertBackOrder(BackOrder backOrder) {
        backOrders.add(backOrder);
        return true;
    }

    /**
     * Removes a backorder from a collection.  
     * @param backOrder BackOrder the back order to be removed.
     * @return returns true if the back order was found and removed, false if not.
     */
    public boolean removeBackOrder(BackOrder backOrder) {
        if (backOrders.contains(backOrder)) {
            backOrders.remove(backOrder);
            return true;
        }
        return false;
    }

    /**
     * Retrieves a list containing all back orders.
     * @return List<BackOrder> list of back orders.
     */
    public List<BackOrder> getBackOrderList() {
        return this.backOrders;
    }

    @Override
    public Iterator<BackOrder> iterator() {
        return backOrders.iterator();
    }
}
