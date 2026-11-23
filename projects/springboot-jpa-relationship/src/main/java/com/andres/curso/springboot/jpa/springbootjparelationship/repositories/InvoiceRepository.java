package com.andres.curso.springboot.jpa.springbootjparelationship.repositories;

import com.andres.curso.springboot.jpa.springbootjparelationship.entities.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
