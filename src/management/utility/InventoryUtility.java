package management.utility;

import management.domain.Item;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryUtility {

    private static volatile ConcurrentHashMap<String, Item> inventory;

    private InventoryUtility() {

    };

    public static ConcurrentHashMap<String, Item> getInventory() {
        if(inventory == null) {
            synchronized (InventoryUtility.class) {
                if(inventory == null) {
                    inventory = new ConcurrentHashMap<String, Item>();
                }
            }
        }

        return inventory;
    }

}
