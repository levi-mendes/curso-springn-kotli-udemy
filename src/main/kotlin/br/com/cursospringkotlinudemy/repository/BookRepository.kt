package br.com.cursospringkotlinudemy.repository

import br.com.cursospringkotlinudemy.BookStatus
import br.com.cursospringkotlinudemy.model.BookModel
import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<BookModel, Int> {

    fun findByStatus(ativo: BookStatus): List<BookModel>
}