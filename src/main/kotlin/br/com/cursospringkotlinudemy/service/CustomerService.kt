package br.com.cursospringkotlinudemy.service

import br.com.cursospringkotlinudemy.enums.CustomerStatus
import br.com.cursospringkotlinudemy.enums.Errors
import br.com.cursospringkotlinudemy.exception.NotFoundException
import br.com.cursospringkotlinudemy.model.CustomerModel
import br.com.cursospringkotlinudemy.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class CustomerService(
    val repository: CustomerRepository,
    val bookService: BookService
) {

    //https://www.baeldung.com/spring-jpa-like-queries

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return repository.findByNameContaining(name)
        }

        return repository.findAll().toList()
    }

    fun update(customer: CustomerModel) {
        if (!repository.existsById(customer.id!!)) {
            throw Exception("Registro nao encontrado")
        }

        repository.save(customer)
    }

    fun findById(id: Int): CustomerModel {
        return repository.findById(id).orElseThrow {
            NotFoundException(Errors.ML201.message.format(id), Errors.ML201.code)
        }
    }

    fun create(customer: CustomerModel) = repository.save(customer)

    fun delete(id: Int) {
        val customer = findById(id)

        customer.status = CustomerStatus.INATIVO
        repository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
        return !repository.existsByEmail(email)
    }
}