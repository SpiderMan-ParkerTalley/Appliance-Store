package edu.ics372.project1.appliancestore.business.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.project1.appliancestore.business.entities.BackOrder;

public class BackOrderList {
    private List<BackOrder> backOrders = new LinkedList<BackOrder>();
    private static BackOrderList backOrderList;

    private BackOrderList() {

    }

    public static BackOrderList getInstance() {
        if (backOrderList == null) {
            backOrderList = new BackOrderList();
        }
        return backOrderList;
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
}
