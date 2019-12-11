package com.project.invoiceHelper;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "invoiceitems")
public class InvoiceItem {
    @Id
    @Column(name = "invoiceItemId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "model")
    private String model;

    @OneToMany(mappedBy = "invoiceItem", cascade = CascadeType.ALL)
    private List<Order> orders;

//    @ManyToMany
//    @JoinTable(
//            name = "orders",
//            joinColumns = @JoinColumn(name = "invoiceitemid"),
//            inverseJoinColumns = @JoinColumn(name = "invoiceNo"))
//    List<Invoice> invoices;


    public InvoiceItem() {
    }

    public InvoiceItem(String model, List<Order> orders) {
        this.model = model;
        this.orders.addAll(orders);
    }

    public InvoiceItem(long id, String model, List<Order> orders) {
        this.id = id;
        this.model = model;
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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
