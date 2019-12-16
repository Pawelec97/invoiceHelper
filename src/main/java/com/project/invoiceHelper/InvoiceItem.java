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



//    @ManyToMany
//    @JoinTable(
//            name = "orders",
//            joinColumns = @JoinColumn(name = "invoiceitemid"),
//            inverseJoinColumns = @JoinColumn(name = "invoiceNo"))
//    List<Invoice> invoices;


    public InvoiceItem() {
    }

    public InvoiceItem(long id, String model) {
        this.id = id;
        this.model = model;
    }

    public InvoiceItem(String model) {
        this.model = model;

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
