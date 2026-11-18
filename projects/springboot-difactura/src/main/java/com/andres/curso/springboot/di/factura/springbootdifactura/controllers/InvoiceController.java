package com.andres.curso.springboot.di.factura.springbootdifactura.controllers;

import com.andres.curso.springboot.di.factura.springbootdifactura.models.Invoice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final Invoice invoice;

    public InvoiceController(Invoice invoice) {
        this.invoice = invoice;
    }

    @GetMapping("/show")
    public Invoice show() {
        return invoice;
    }
}
