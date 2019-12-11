package com.project.invoiceHelper;

import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderNo")
    private long orderNo;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoiceNo")
    private Invoice invoice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoiceItemId")
    private InvoiceItem invoiceItem;


    public Order (){
    }


    public Order(long quantity, BigDecimal price, Invoice invoice, InvoiceItem invoiceItem) {
        this.quantity = quantity;
        this.price = price;
        this.invoice = invoice;
        this.invoiceItem = invoiceItem;
    }

    public Order(long quantity, BigDecimal price, InvoiceItem invoiceItem) {
        this.quantity = quantity;
        this.price = price;
        this.invoiceItem = invoiceItem;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public InvoiceItem getInvoiceItem() {
        return invoiceItem;
    }

    public void setInvoiceItem(InvoiceItem invoiceItem) {
        this.invoiceItem = invoiceItem;
    }
}
