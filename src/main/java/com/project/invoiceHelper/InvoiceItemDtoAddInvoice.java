package com.project.invoiceHelper;

import java.math.BigDecimal;


public class InvoiceItemDtoAddInvoice {
    private long id;
    private long quantity;
    private BigDecimal price;



    public InvoiceItemDtoAddInvoice() {

    }

    public InvoiceItemDtoAddInvoice(long id, long quantity, BigDecimal price) {
        this.id = id;
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


    }


