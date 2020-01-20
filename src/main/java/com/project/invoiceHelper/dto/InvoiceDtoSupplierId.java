package com.project.invoiceHelper.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDtoSupplierId {

	private long invoiceNo;
	private LocalDate creationDate;
	private long supplierId;

}
