package management;

import management.exception.ItemNotEnoughException;
import management.exception.ItemNotFoundException;
import management.service.ItemService;
import management.service.ItemServiceImpl.ItemServiceImpl;

import java.math.BigDecimal;
import java.util.Scanner;

public class application {

    public static void main(String[] args) {
        ItemService service = new ItemServiceImpl();
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        String command = "";
        String name = "";
        BigDecimal costPrice = BigDecimal.ZERO;
        BigDecimal sellingPrice = BigDecimal.ZERO;
        int quantity = 0;
        while(true) {
            System.out.print("Enter command: ");
            command = reader.next(); // Scans the next
            switch (command) {
                case "create":
                    name = reader.next();
                    costPrice = reader.nextBigDecimal();
                    sellingPrice = reader.nextBigDecimal();
                    service.create(name, costPrice, sellingPrice);
                    break;
                case "updateBuy":
                    name = reader.next();
                    quantity = reader.nextInt();
                    try {
                        service.updateBuy(name, quantity);
                    } catch (ItemNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "updateSell":
                    name = reader.next();
                    quantity = reader.nextInt();
                    try {
                        service.updateSell(name, quantity);
                    } catch (ItemNotFoundException | ItemNotEnoughException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "delete":
                    name = reader.next();
                    try {
                        service.delete(name);
                    } catch (ItemNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "updateSellPrice":
                    name = reader.next();
                    sellingPrice = reader.nextBigDecimal();
                    try {
                        service.updateSellPrice(name, sellingPrice);
                    } catch (ItemNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "report":
                    System.out.println(service.report());
                    break;
                default:
                    System.out.println("no such command");
                    break;
            }
        }
        //reader.close(); create Book01 10.50 13.79
    }
}
