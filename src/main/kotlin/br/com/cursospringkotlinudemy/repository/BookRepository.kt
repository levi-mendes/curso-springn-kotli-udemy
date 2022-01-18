package br.com.cursospringkotlinudemy.repository

import br.com.cursospringkotlinudemy.BookStatus
import br.com.cursospringkotlinudemy.model.BookModel
import br.com.cursospringkotlinudemy.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<BookModel, Int> {

    fun findByStatus(ativo: BookStatus): List<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>
}