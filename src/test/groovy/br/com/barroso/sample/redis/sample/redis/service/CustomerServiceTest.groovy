package br.com.barroso.sample.redis.sample.redis.service

import br.com.barroso.sample.redis.sample.redis.domain.Customer
import br.com.barroso.sample.redis.sample.redis.exception.CustomerAlreadyException
import br.com.barroso.sample.redis.sample.redis.exception.CustomerNotFoundException
import br.com.barroso.sample.redis.sample.redis.repository.CustomerRepository
import org.springframework.test.annotation.DirtiesContext
import br.com.barroso.sample.redis.sample.redis.service.CustomerService
import spock.lang.Specification

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class CustomerServiceTest extends Specification {

    CustomerRepository repositoryMock
    CustomerService service

    void setup() {
        this.repositoryMock = Mock(CustomerRepository)
        this.service = new CustomerService(repositoryMock)
    }

    void "should return CustomerAlreadyException error if entity exists"() {
        given:
            def customer = new Customer()
            customer.id = 1L
        when:
            this.service.createCustomer(customer)
        then:
            this.repositoryMock.findAll() >> [customer]
            this.repositoryMock.save(*_) >> customer
            def ex = thrown(CustomerAlreadyException)
            ex
            ex.message == "Product ${customer.id} already registered!"
    }

    void "should return entity by ID"() {
        given:
            def customer = new Customer()
            customer.id = 1L
        when:
            def result = this.service.findById(customer.id)
        then:
            this.repositoryMock.findById(*_) >> Optional.ofNullable(customer)
            result
    }

    void "should return CustomerNotFoundException error if entity not exists"() {
        given:
            def customer = new Customer()
            customer.id = 1L
        when:
            this.service.findById(customer.id)
        then:
            this.repositoryMock.findById(*_) >> Optional.empty()
            def ex = thrown(CustomerNotFoundException)
            ex
            ex.message == "Product ${customer.id} not found!"
    }

    void "should return all entities"() {
        when:
            def result = this.service.findAll()
        then:
            this.repositoryMock.findAll() >> [new Customer(), new Customer()]
            result
            result.size() == 2
    }

    void "should return CustomerNotFoundException error if entity not exists to update"() {
        given:
            def customer = new Customer()
            customer.id = 1L
        when:
            this.service.updateCustomer(customer)
        then:
            this.repositoryMock.findAll() >> []
            def ex = thrown(CustomerNotFoundException)
            ex
            ex.message == "Product ${customer.id} not found!"
    }

    void "should return CustomerNotFoundException error if entity not exists to delete"() {
        given:
            def customer = new Customer()
            customer.id = 1L
        when:
            this.service.deleteCustomerById(customer.id)
        then:
            this.repositoryMock.findAll() >> []
            def ex = thrown(CustomerNotFoundException)
            ex
            ex.message == "Product ${customer.id} not found!"
    }

}
