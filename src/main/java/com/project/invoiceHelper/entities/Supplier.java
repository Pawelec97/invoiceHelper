package com.project.invoiceHelper.entities;


import com.project.invoiceHelper.entities.Invoice;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String address;

	@OneToMany(mappedBy = "supplier")
	private List<Invoice> invoices;

	public Supplier(long id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
		invoices = new ArrayList<>();
	}

}
