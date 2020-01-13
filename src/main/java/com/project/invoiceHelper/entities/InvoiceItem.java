package com.project.invoiceHelper.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoiceitems")
public class InvoiceItem {

	@Id
	@Column(name = "invoiceItemId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "model")
	private String model;

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
