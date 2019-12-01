package br.com.barroso.sample.redis.sample.redis.controller

import br.com.barroso.sample.redis.sample.redis.domain.Customer
import br.com.barroso.sample.redis.sample.redis.service.CustomerService
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/customers")
class CustomerController( val customerService: CustomerService ) {

    @PostMapping()
    fun createCustomer( @Valid @RequestBody(required = true) customer: Customer ): Customer {
        return this.customerService.createCustomer(customer)
    }

    @GetMapping("/{customerId}")
    fun findById( @PathVariable( name = "customerId", required = true ) customerId: Long ): Customer? {
        return this.customerService.findById(customerId)
    }

    @GetMapping()
    fun findAll(): List<Customer?> {
        return this.customerService.findAll()
    }

    @PutMapping()
    fun updateCustomer( @Valid @RequestBody(required = true) customer: Customer ) {
        this.customerService.updateCustomer(customer)
    }

    @DeleteMapping("/{customerId}")
    fun deleteCustomerById( customerId: Long ) {
        this.customerService.deleteCustomerById(customerId)
    }

}