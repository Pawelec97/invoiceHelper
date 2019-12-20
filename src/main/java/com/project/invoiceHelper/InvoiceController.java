package com.project.invoiceHelper;

import com.project.invoiceHelper.dto.*;
import com.project.invoiceHelper.repositories.InvoiceItemRepository;
import com.project.invoiceHelper.repositories.InvoiceRepository;
import com.project.invoiceHelper.repositories.OrderRepository;
import com.project.invoiceHelper.repositories.SupplierRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

  /*  @RequestMapping("/invoicestest")
    public Invoice invoiceTest() {

        InvoiceItem[] items = {new InvoiceItem(3,15.5,"model",12)};

        return new Invoice(31, 13);
    }*/


//    @GetMapping("/invoices")
////    public List<InvoiceDto> findAll(){
////        List<Invoice> invoices = invoiceRepository.findAll();
////        List<InvoiceDto> invoicesDto = new ArrayList<>();
////        for (Invoice invoice: invoices){
////            invoicesDto.add(new InvoiceDto(invoice.getInvoiceNo(), invoice.getCreationDate(),
////                    new SupplierDto(invoice.getSupplier().getId(),invoice.getSupplier().getName(),invoice.getSupplier().getAddress())));
////        }
////        return invoicesDto;
////    }


    public InvoiceController() {
    }

    public InvoiceController(InvoiceRepository invoiceRepository, SupplierRepository supplierRepository, InvoiceItemRepository invoiceItemRepository, OrderRepository orderRepository) {
        this.invoiceRepository = invoiceRepository;
        this.supplierRepository = supplierRepository;
        this.invoiceItemRepository = invoiceItemRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/invoices")
    public List<InvoiceWithOrdersDto> findAll(@RequestParam("page") int page,
                                              @RequestParam("size") int size){
        List<Invoice> invoices = invoiceRepository.findAll(PageRequest.of(page, size)).getContent();
        List<InvoiceWithOrdersDto> invoicesDto = new ArrayList<>();
        for (Invoice invoice: invoices){
            List<InvoiceItemDto> itemsDto = new ArrayList<>();
            for(Order order: invoice.getOrders()){
                Optional<InvoiceItem> itemOptional = invoiceItemRepository.findById(order.getInvoiceItem().getId()); // szukam
                if(itemOptional.isPresent())
                {
                    InvoiceItem item = itemOptional.get();
                    itemsDto.add(new InvoiceItemDto(item.getId(), item.getModel(), order.getQuantity(), order.getPrice()));
                }
            }

            invoicesDto.add(new InvoiceWithOrdersDto(invoice.getInvoiceNo(), invoice.getCreationDate(),
                    new SupplierDto(invoice.getSupplier().getId(),invoice.getSupplier().getName(),invoice.getSupplier().getAddress()),
                    itemsDto));
        }
        return invoicesDto;
    }

    @GetMapping("/invoices/{invoiceNo}")
    public ResponseEntity<InvoiceWithOrdersDto> findOne(@PathVariable("invoiceNo") long invoiceNo){
        List<Invoice> invoices = invoiceRepository.findAll();

        if(invoices.stream().noneMatch(element -> element.getInvoiceNo() == invoiceNo)) return new ResponseEntity<>(null,HttpStatus.CONFLICT);

        Invoice invoice = (invoices.stream().filter(element -> element.getInvoiceNo() == invoiceNo).findFirst()).get();

        List<InvoiceItemDto> itemsDto = new ArrayList<>();
        for(Order order: invoice.getOrders()){
            itemsDto.add(new InvoiceItemDto( order.getInvoiceItem().getId(), order.getInvoiceItem().getModel(), order.getQuantity(), order.getPrice()));
        }

        InvoiceWithOrdersDto invoiceDto=  new InvoiceWithOrdersDto(invoice.getInvoiceNo(), invoice.getCreationDate(),
                new SupplierDto(invoice.getSupplier().getId(),invoice.getSupplier().getName(),invoice.getSupplier().getAddress()),
                itemsDto);

        return new ResponseEntity<>(invoiceDto,HttpStatus.OK);
    }

    @PostMapping(path = "/invoices", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addInvoice(@RequestBody InvoiceAddDto invoiceDto){
        if(invoiceRepository.findById(invoiceDto.getInvoiceNo()).isPresent()){
            return new ResponseEntity<>("this InvoiceNo is exist"+invoiceDto.getInvoiceNo(), HttpStatus.CONFLICT);
        }

        List<Order> orders = new ArrayList<>();
        for(InvoiceItemDtoAddInvoice invoiceItemDto: invoiceDto.getItems()){ //dla każdego podanego itemu
            Optional<InvoiceItem> item = invoiceItemRepository.findById(invoiceItemDto.getId());
            if(item.isPresent()){
                orders.add(new Order(invoiceItemDto.getQuantity(), invoiceItemDto.getPrice(), item.get())); //tworze zamowienie bez podanej faktury
            }else
                return new ResponseEntity<>("this itemID isnt exist", HttpStatus.CONFLICT);
        }


//         Optional<List<Order>> orders= invoiceItemRepository.findAll().stream()
//                .filter(e -> invoiceDto.getInvoiceItemIds().)

        Optional<Supplier> supplier = supplierRepository.findById(invoiceDto.getSupplier());


        if(supplier.isPresent()){
            Invoice inv = new Invoice(invoiceDto.getInvoiceNo(),invoiceDto.getCreationDate(),
                    supplier.get(),orders);
            Invoice save = invoiceRepository.save(inv);
            for(Order o : orders){
                o.setInvoice(save);
                orderRepository.save(o);
            }


            return new ResponseEntity<>("ok ", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad Supplier ID", HttpStatus.CONFLICT);

    }





}
