package com.project.invoiceHelper.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoiceitems")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItem {

	@Id
	@Column(name = "invoiceItemId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "model")
	private String model;
}