package management.service.ItemServiceImpl;

import management.domain.Item;
import management.domain.Report;
import management.exception.ItemNotEnoughException;
import management.exception.ItemNotFoundException;
import management.service.ItemService;
import management.utility.InventoryReportUtility;
import management.utility.InventoryUtility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class ItemServiceImpl implements ItemService{

    private final ConcurrentHashMap<String, Item> inventory = InventoryUtility.getInventory();
    private final LinkedBlockingDeque<Report> inventoryReportList = InventoryReportUtility.getInventoryReportList();

    /*
    * 1. Delete item according name
    * 2. update current report data after delete item
    * */
    @Override
    public boolean delete(String name) throws ItemNotFoundException {
        Report report = getCurrentReport();
        if(inventory.containsKey(name)) {
            Item item = inventory.get(name);
            synchronized (item) {
                report.setProfit(report.getProfit().subtract(item.getCostPrice().multiply(new BigDecimal(item.getAvailableQty()))));
                inventory.remove(name);
                return true;
            }
        }
        throw new ItemNotFoundException("no such item");
    }

    /*
    * Get existing item object or create a new object
    * */
    @Override
    public boolean create(String name, BigDecimal costPrice, BigDecimal sellingPrice) {
        Item item = inventory.getOrDefault(name, new Item(name, sellingPrice.setScale(2, RoundingMode.HALF_UP), costPrice.setScale(2, RoundingMode.HALF_UP)));
        inventory.put(name, item);
        return true;
    }


    @Override
    public boolean updateBuy(String name, int quantity) throws ItemNotFoundException {
        if(inventory.containsKey(name)) {
            Item item = inventory.get(name);
            synchronized (item) {
                item.setAvailableQty(item.getAvailableQty() + quantity);
                return true;
            }
        }
        throw new ItemNotFoundException("no such item");
    }

    /*
    * 1. set a new selling record
    * 2. check available quantity of this item if not enough then throw exception
    * 3. update report if successful
    * */
    @Override
    public boolean updateSell(String name, int quantity) throws ItemNotFoundException, ItemNotEnoughException {
        Report report = getCurrentReport();
        if(inventory.containsKey(name)) {
            Item item = inventory.get(name);
            //no enough items
            synchronized (item) {
                if (quantity > item.getAvailableQty())
                    throw new ItemNotEnoughException("item not enough");
                //update report result
                report.setProfit(report.getProfit().add(item.getSellingPrice().subtract(item.getCostPrice()).multiply(new BigDecimal(quantity))));
                item.setAvailableQty(item.getAvailableQty() - quantity);
                item.setSellingQty(item.getSellingQty() + quantity);
                return true;
            }
        }
        throw new ItemNotFoundException("no such item");
    }

    /*
    * update new selling price
    * */
    @Override
    public boolean updateSellPrice(String name, BigDecimal newSellPrice) throws ItemNotFoundException {
        if(inventory.containsKey(name)) {
            Item item = inventory.get(name);
            synchronized (item) {
                item.setSellingPrice(newSellPrice);
            }
            inventory.put(name, item);
            return true;
        }
        throw new ItemNotFoundException("no such item");
    }

    /*
    * caculate totalvalue and set items for this report
    * */
    @Override
    public Report report() {
        Report report = getCurrentReport();
        report = inventoryReportList.getFirst();
        synchronized (report) {
            if (!report.isReportDone()) {
                //create new items list in report
                report.setItems(new ConcurrentHashMap<String, Item>(inventory));
                report.setTotalValue(report
                        .getItems()
                        .entrySet()
                        .stream()
                        .map(x -> x.getValue())
                        .reduce(BigDecimal.ZERO, (sum, y) -> sum.add(y.getCostPrice().multiply(new BigDecimal(y.getAvailableQty()))), (sum1, sum2) -> sum1.add(sum2)
                        ));
                report.setReportDone(true);
            }
        }
        return report;
    }

    /*
    * get current report or create new one
    * */
    private Report getCurrentReport() {
        Report report = null;
        if(inventoryReportList.isEmpty() || inventoryReportList.getFirst().isReportDone()) {
            report = new Report("Inventory Report");
            //get reference from inventory
            report.setItems(inventory);
            inventoryReportList.addFirst(report);
        }
        return inventoryReportList.getFirst();
    }
}
