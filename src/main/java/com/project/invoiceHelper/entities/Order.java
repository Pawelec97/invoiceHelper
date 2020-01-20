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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

	public Order(long quantity, BigDecimal price, InvoiceItem invoiceItem) {
		this.quantity = quantity;
		this.price = price;
		this.invoiceItem = invoiceItem;
	}
}
