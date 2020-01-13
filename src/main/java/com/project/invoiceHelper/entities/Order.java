package com.project.invoiceHelper.entities;


import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderNo")
	private long orderNo;

	@Column(name = "quantity")
	private long quantity;

	@Column(name = "price")
	private BigDecimal price;


	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoiceItemId")
	private InvoiceItem invoiceItem;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoiceINo")
	private Invoice invoice;


	public Order() {
	}


	public Order(long quantity, BigDecimal price, InvoiceItem invoiceItem) {
		this.quantity = quantity;
		this.price = price;
		this.invoiceItem = invoiceItem;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
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

	public InvoiceItem getInvoiceItem() {
		return invoiceItem;
	}

	public void setInvoiceItem(InvoiceItem invoiceItem) {
		this.invoiceItem = invoiceItem;
	}
}
