package com.project.invoiceHelper;

import java.util.List;

public class SupplierDto {
    private long id;
    private String name;
    private String address;
  //  private List<Invoice> invoices;

    public SupplierDto(){

    }
    public SupplierDto ( long id, String name,String address){
        this.address =address;
        this.id = id;
        this.name = name;
    //    this.invoices.addAll(invoices);
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public List<Invoice> getInvoices() {
//        return invoices;
//    }
//
//    public void setInvoices(List<Invoice> invoices) {
//        this.invoices = invoices;
//    }
}
