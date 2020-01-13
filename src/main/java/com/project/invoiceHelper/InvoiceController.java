package com.project.invoiceHelper;


import com.project.invoiceHelper.dto.InvoiceAddDto;
import com.project.invoiceHelper.dto.InvoiceItemDto;
import com.project.invoiceHelper.dto.InvoiceItemDtoAddInvoice;
import com.project.invoiceHelper.dto.InvoiceWithOrdersDto;
import com.project.invoiceHelper.dto.SupplierDto;
import com.project.invoiceHelper.entities.Invoice;
import com.project.invoiceHelper.entities.InvoiceItem;
import com.project.invoiceHelper.entities.Order;
import com.project.invoiceHelper.entities.Supplier;
import com.project.invoiceHelper.repositories.InvoiceItemRepository;
import com.project.invoiceHelper.repositories.InvoiceRepository;
import com.project.invoiceHelper.repositories.OrderRepository;
import com.project.invoiceHelper.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InvoiceController {

	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	SupplierRepository supplierRepository;
	@Autowired
	InvoiceItemRepository invoiceItemRepository;
	@Autowired
	OrderRepository orderRepository;

	public InvoiceController() {
	}

	public InvoiceController(InvoiceRepository invoiceRepository,
			SupplierRepository supplierRepository, InvoiceItemRepository invoiceItemRepository,
			OrderRepository orderRepository) {
		this.invoiceRepository = invoiceRepository;
		this.supplierRepository = supplierRepository;
		this.invoiceItemRepository = invoiceItemRepository;
		this.orderRepository = orderRepository;
	}

	@GetMapping("/invoices")
	public List<InvoiceWithOrdersDto> findAll() {
		List<Invoice> invoices = invoiceRepository.findAll();
		List<InvoiceWithOrdersDto> invoicesDto = new ArrayList<>();
		for (Invoice invoice : invoices) {
			List<InvoiceItemDto> itemsDto = new ArrayList<>();
			for (Order order : invoice.getOrders()) {
				Optional<InvoiceItem> itemOptional = invoiceItemRepository
						.findById(order.getInvoiceItem().getId()); // szukam
				if (itemOptional.isPresent()) {
					InvoiceItem item = itemOptional.get();
					itemsDto.add(new InvoiceItemDto(item.getId(), item.getModel(), order.getQuantity(),
							order.getPrice()));
				}
			}

			invoicesDto.add(new InvoiceWithOrdersDto(invoice.getInvoiceNo(), invoice.getCreationDate(),
					new SupplierDto(invoice.getSupplier().getId(), invoice.getSupplier().getName(),
							invoice.getSupplier().getAddress()),
					itemsDto));
		}
		return invoicesDto;
	}

	@GetMapping("/invoicesPages")
	public List<InvoiceWithOrdersDto> findAll(@RequestParam("page") int page,
			@RequestParam("size") int size,
			@Param("supplierId") Long supplierId,
			@Param("startDate") @DateTimeFormat(pattern = "yyyy-MM-DD") LocalDate startDate,
			@Param("endDate") @DateTimeFormat(pattern = "yyyy-MM-DD") LocalDate endDate) {

		InvoiceSpecification specification = new InvoiceSpecification();

		if (supplierId != null) {
			specification.add(new SearchCriteria("supplier", new Supplier(supplierId, null, null),
					SearchOperation.EQUAL));
		}
		if (startDate != null) {
			specification.add(new SearchCriteria("creationDate", startDate, SearchOperation.GREATER_THAN_EQUAL_LOCALDATE));
		}
		if (endDate != null) {
			specification.add(new SearchCriteria("creationDate", endDate, SearchOperation.LESS_THAN_EQUAL_LOCALDATE));
		}
		List<Invoice> invoices = invoiceRepository.findAll(specification);

		List<InvoiceWithOrdersDto> invoicesDto = new ArrayList<>();
		for (Invoice invoice : invoices) {
			List<InvoiceItemDto> itemsDto = new ArrayList<>();
			for (Order order : invoice.getOrders()) {
				Optional<InvoiceItem> itemOptional = invoiceItemRepository
						.findById(order.getInvoiceItem().getId());
				if (itemOptional.isPresent()) {
					InvoiceItem item = itemOptional.get();
					itemsDto.add(new InvoiceItemDto(item.getId(), item.getModel(), order.getQuantity(),
							order.getPrice()));
				}
			}

			invoicesDto.add(new InvoiceWithOrdersDto(invoice.getInvoiceNo(), invoice.getCreationDate(),
					new SupplierDto(invoice.getSupplier().getId(), invoice.getSupplier().getName(),
							invoice.getSupplier().getAddress()),
					itemsDto));
		}
		return invoicesDto;
	}

	@GetMapping("/invoices/{invoiceNo}")
	public ResponseEntity<InvoiceWithOrdersDto> findOne(@PathVariable("invoiceNo") long invoiceNo) {
		List<Invoice> invoices = invoiceRepository.findAll();

		if (invoices.stream().noneMatch(element -> element.getInvoiceNo() == invoiceNo)) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}

		Invoice invoice = (invoices.stream().filter(element -> element.getInvoiceNo() == invoiceNo)
				.findFirst()).get();

		List<InvoiceItemDto> itemsDto = new ArrayList<>();
		for (Order order : invoice.getOrders()) {
			itemsDto.add(
					new InvoiceItemDto(order.getInvoiceItem().getId(), order.getInvoiceItem().getModel(),
							order.getQuantity(), order.getPrice()));
		}

		InvoiceWithOrdersDto invoiceDto = new InvoiceWithOrdersDto(invoice.getInvoiceNo(),
				invoice.getCreationDate(),
				new SupplierDto(invoice.getSupplier().getId(), invoice.getSupplier().getName(),
						invoice.getSupplier().getAddress()),
				itemsDto);

		return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
	}

	@PostMapping(path = "/invoices", produces = {MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addInvoice(@RequestBody InvoiceAddDto invoiceDto) {
		if (invoiceRepository.findById(invoiceDto.getInvoiceNo()).isPresent()) {
			return new ResponseEntity<>("this InvoiceNo is exist" + invoiceDto.getInvoiceNo(),
					HttpStatus.CONFLICT);
		}

		List<Order> orders = new ArrayList<>();
		for (InvoiceItemDtoAddInvoice invoiceItemDto : invoiceDto.getItems()) {
			Optional<InvoiceItem> item = invoiceItemRepository.findById(invoiceItemDto.getId());
			if (item.isPresent()) {
				orders.add(
						new Order(invoiceItemDto.getQuantity(), invoiceItemDto.getPrice(), item.get()));
			} else {
				return new ResponseEntity<>("this itemID isnt exist", HttpStatus.CONFLICT);
			}
		}

		Optional<Supplier> supplier = supplierRepository.findById(invoiceDto.getSupplier());

		if (supplier.isPresent()) {
			Invoice inv = new Invoice(invoiceDto.getInvoiceNo(), invoiceDto.getCreationDate(),
					supplier.get(), orders);
			Invoice save = invoiceRepository.save(inv);
			for (Order o : orders) {
				o.setInvoice(save);
				orderRepository.save(o);
			}

			return new ResponseEntity<>("ok ", HttpStatus.OK);
		}
		return new ResponseEntity<>("Bad Supplier ID", HttpStatus.CONFLICT);

	}


}
