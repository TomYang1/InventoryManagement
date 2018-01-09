package management.domain;

import management.service.ItemServiceImpl.ItemServiceImpl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Report {
    private int id;
    private String name;
    private ConcurrentHashMap<String, Item> items;
    private BigDecimal totalValue = BigDecimal.ZERO;
    private BigDecimal profit = BigDecimal.ZERO;

    private boolean reportDone = false;

    public Report(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConcurrentHashMap<String, Item> getItems() {
        return items;
    }

    public void setItems(ConcurrentHashMap<String, Item> items) {
        this.items = items;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public boolean isReportDone() {
        return reportDone;
    }

    public void setReportDone(boolean reportDone) {
        this.reportDone = reportDone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String shortSplit = new String(new char[10]).replace("\0", "-");
        sb.append(String.format(" %50s\n %-20s %-20s %-20s %-20s %-20s\n %-20s %-20s %-20s %-20s %-20s\n",
                this.name, "Item Name", "Bought At", "Sold At", "AvailableQty", "Value", shortSplit, shortSplit, shortSplit, shortSplit, shortSplit));
        this.items.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(x -> {
                    Item item = x.getValue();
                    sb.append(String.format(" %-20s %-20s %-20s %-20s %-20s\n", item.getName(), item.getCostPrice(), item.getSellingPrice(), item.getAvailableQty(), item.getCostPrice().multiply(new BigDecimal(item.getAvailableQty()))));
                });
        sb.append(new String(new char[100]).replace("\0", "-"));
        sb.append(String.format("\n %-50s %50s\n ", "Total value", this.totalValue));
        sb.append(String.format("%-50s %50s\n ", "Profit since previous report", this.profit));
        return sb.toString();
    }
}
