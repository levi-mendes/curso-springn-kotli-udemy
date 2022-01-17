package br.com.cursospringkotlinudemy.repository

import br.com.cursospringkotlinudemy.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository: CrudRepository<CustomerModel, Int> {

    fun findByNameContaining(name: String): List<CustomerModel>
}