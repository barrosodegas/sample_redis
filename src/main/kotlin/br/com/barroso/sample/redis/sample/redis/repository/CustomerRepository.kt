package br.com.barroso.sample.redis.sample.redis.repository

import br.com.barroso.sample.redis.sample.redis.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, Long>