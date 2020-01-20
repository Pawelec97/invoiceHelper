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
public class InvoiceCountItemsDto {
	private Long invoiceNo;
	private LocalDate creationDate;
	private Long supplierId;
	private int orderCount;

}
