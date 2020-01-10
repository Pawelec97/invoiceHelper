package com.project.invoiceHelper.dto;


public class SupplierDto {
    private long id;
    private String name;
    private String address;

    public SupplierDto() {

    }

    public SupplierDto(long id, String name, String address) {
        this.address = address;
        this.id = id;
        this.name = name;
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
}
