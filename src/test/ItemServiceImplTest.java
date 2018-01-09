package test;

import management.exception.ItemNotEnoughException;
import management.exception.ItemNotFoundException;
import management.service.ItemService;
import management.service.ItemServiceImpl.ItemServiceImpl;
import management.utility.InventoryUtility;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ItemServiceImplTest{

    @Test
    public void reportTest() {
        ItemService service = new ItemServiceImpl();
        try {
            service.create("Book01", new BigDecimal(10.5), new BigDecimal(13.79));
            service.create("Food01", new BigDecimal(1.47), new BigDecimal(3.98));
            service.create("Med01", new BigDecimal(30.63), new BigDecimal(34.29));
            service.create("Tab01", new BigDecimal(57.00), new BigDecimal(84.98));
            service.updateBuy("Tab01", 100);
            service.updateSell("Tab01", 2);
            service.updateBuy("Food01", 500);
            service.updateBuy("Book01", 100);
            service.updateBuy("Med01", 100);
            service.updateSell("Food01", 1);
            service.updateSell("Food01", 1);
            service.updateSell("Tab01", 2);
            System.out.println(service.report());
            System.out.println();

            service.delete("Book01");
            service.updateSell("Tab01", 5);
            service.create("Mobile01", new BigDecimal(10.51), new BigDecimal(44.56));
            service.updateBuy("Mobile01", 250);
            service.updateSell("Food01", 5);
            service.updateSell("Mobile01", 4);
            service.updateSell("Med01", 10);
            System.out.println(service.report());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void updateSellExceptionTest(){
        ItemService service = new ItemServiceImpl();
        try {
            service.updateSell("test", 10);
        } catch (ItemNotFoundException e) {
            return;
        } catch (ItemNotEnoughException e) {
            return;
        }
    }

    @Test
    public void createItemTest() {
        ItemService service = new ItemServiceImpl();
        service.create("apple", new BigDecimal(1), new BigDecimal(2));
        Assert.assertTrue(InventoryUtility.getInventory().containsKey("apple"));
    }

    @Test
    public void updateBuyTest() {
        ItemService service = new ItemServiceImpl();
        service.create("apple", new BigDecimal(1), new BigDecimal(2));
        try {
            Assert.assertTrue(service.updateBuy("apple", 100));
        } catch (ItemNotFoundException e) {
            Assert.fail();
        }
    }
}
