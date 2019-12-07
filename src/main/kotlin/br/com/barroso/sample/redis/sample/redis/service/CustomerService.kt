package br.com.barroso.sample.redis.sample.redis.service

import br.com.barroso.sample.redis.sample.redis.domain.Customer
import br.com.barroso.sample.redis.sample.redis.exception.CustomerAlreadyException
import br.com.barroso.sample.redis.sample.redis.exception.CustomerNotFoundException
import br.com.barroso.sample.redis.sample.redis.repository.CustomerRepository
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
@GraphQLApi
class CustomerService( val repository: CustomerRepository ) {

    // Invalidates all cache data.
    @CacheEvict(cacheNames = [Customer.CACHE_NAME], allEntries = true)
    @GraphQLMutation(name = "createCustomer")
    fun createCustomer( customer: Customer ): Customer {

        this.findAll().stream().filter { it?.id  == customer.id }
                .findFirst().ifPresent { throw CustomerAlreadyException("Product ${it.id} already registered!") }

        return this.repository.save(customer)
    }

    // From the first query, it caches the record by binding it to the ID.
    // Storage Standard --> Key: Customer:id Value: entity
    @Cacheable(cacheNames = [Customer.CACHE_NAME], key = "#customerId")
    @GraphQLQuery(name = "findById")
    fun findById( customerId: Long ): Customer? {
        return this.repository.findById(customerId).orElseThrow { this.notFound(customerId) }
    }

    // From the first query, it caches all records by linking them to the name of the executed method.
    // These records will be invalidated in the cache every time there is a change or if the cache expires.
    // Storage Standard --> Key: Customer:findAll Value: entity list
    @Cacheable(cacheNames = [Customer.CACHE_NAME], key = "#root.method.name")
    @GraphQLQuery(name = "findAll")
    fun findAll(): List<Customer?> {
        return this.repository.findAll()
    }

    // Updates a specific record in the cache.
    // Storage Standard --> Key: Customer:id Value: entity
    @CachePut(cacheNames = [Customer.CACHE_NAME], key = "#customer.id")
    @GraphQLMutation(name = "updateCustomer")
    fun updateCustomer( customer: Customer ) {

        this.findAll().stream().filter { it?.id  == customer.id }
                .findFirst().orElseThrow { this.notFound(customer.id) }

        this.repository.save(customer)
    }

    // Removes a specific record from the cache.
    @CacheEvict(cacheNames = [Customer.CACHE_NAME], key = "#customerId")
    @GraphQLMutation(name = "deleteCustomerById")
    fun deleteCustomerById( customerId: Long ) {

        this.findAll().stream().filter { it?.id  == customerId }
                .findFirst().orElseThrow { this.notFound(customerId) }

        this.repository.deleteById(customerId)
    }

    private fun notFound(customerId: Long): CustomerNotFoundException {
        return CustomerNotFoundException("Product $customerId not found!")
    }
}