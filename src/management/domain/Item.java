package management.domain;

import java.math.BigDecimal;

public class Item {
    private int id;
    private String name;
    private BigDecimal sellingPrice;
    private BigDecimal costPrice;
    private int availableQty;
    private int sellingQty;

    public Item(String name, BigDecimal sellingPrice, BigDecimal costPrice) {
        this.name = name;
        this.sellingPrice = sellingPrice;
        this.costPrice = costPrice;
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

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    public int getSellingQty() {
        return sellingQty;
    }

    public void setSellingQty(int sellingQty) {
        this.sellingQty = sellingQty;
    }
}
