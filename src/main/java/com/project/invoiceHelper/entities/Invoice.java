package com.project.invoiceHelper.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

	@Id
	@Column(name = "invoiceNo")
	private Long invoiceNo;

	@Column(name = "creationDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD")
	private LocalDate creationDate;

	@ManyToOne
	@JoinColumn(name = "supplierId")
	private Supplier supplier;

	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	@Column(name = "order")
	private List<Order> orders;

	public Invoice(Long invoiceNo, LocalDate creationDate, Supplier supplier) {
		this.invoiceNo = invoiceNo;
		this.creationDate = creationDate;
		this.supplier = supplier;
	}

}
