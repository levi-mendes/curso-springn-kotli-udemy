package br.com.cursospringkotlinudemy.repository

import br.com.cursospringkotlinudemy.BookStatus
import br.com.cursospringkotlinudemy.model.BookModel
import br.com.cursospringkotlinudemy.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<BookModel, Int> {

    fun findByStatus(ativo: BookStatus, pageable: Pageable): Page<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>
//    fun findAll(pageable: Pageable): Page<BookModel>
}