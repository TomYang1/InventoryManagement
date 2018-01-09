package management.service;

import management.domain.Report;
import management.exception.ItemNotEnoughException;
import management.exception.ItemNotFoundException;

import java.math.BigDecimal;

public interface ItemService {
    boolean delete(String name) throws ItemNotFoundException;
    boolean create(String name, BigDecimal costPrice, BigDecimal sellingPrice);
    boolean updateBuy(String name, int quantity) throws ItemNotFoundException;
    boolean updateSell(String name, int quantity) throws ItemNotFoundException, ItemNotEnoughException;
    boolean updateSellPrice(String name, BigDecimal newSellPrice) throws ItemNotFoundException;

    Report report();
}
