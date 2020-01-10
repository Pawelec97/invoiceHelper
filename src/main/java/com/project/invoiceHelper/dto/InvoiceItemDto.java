package com.project.invoiceHelper.dto;

import java.math.BigDecimal;

public class InvoiceItemDto {
    private long id;
    private String model;
    private long quantity;
    private BigDecimal price;


    public InvoiceItemDto() {

    }

    public InvoiceItemDto(long id, String model, long quantity, BigDecimal price) {
        this.id = id;
        this.model = model;
        this.quantity = quantity;
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
