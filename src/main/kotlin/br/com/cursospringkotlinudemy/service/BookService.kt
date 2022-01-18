package br.com.cursospringkotlinudemy.service

import br.com.cursospringkotlinudemy.BookStatus
import br.com.cursospringkotlinudemy.model.BookModel
import br.com.cursospringkotlinudemy.model.CustomerModel
import br.com.cursospringkotlinudemy.repository.BookRepository
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class BookService(
    val repoitory: BookRepository
) {

    fun getBooks(): List<BookModel> {
        return repoitory.findAll().toList()
    }

    fun findActives(): List<BookModel> {
        return repoitory.findByStatus(BookStatus.ATIVO)
    }

    fun getById(id: Int): BookModel {
        return repoitory.findById(id).orElseThrow()
    }

    fun create(book: BookModel) {
        repoitory.save(book)
    }

    fun deleteById(id: Int) {
        val book = findById(id)

        book.status = BookStatus.CANCELADO

        update(book)
    }

    fun findById(id: Int): BookModel = repoitory.findById(id).orElseThrow()

    fun update(book: BookModel) {
        if (!repoitory.existsById(book.id!!)) {
            throw Exception("Regitro nao encontrado")
        }

        repoitory.save(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = repoitory.findByCustomer(customer)

        books.forEach { it.status = BookStatus.DELETADO }
        repoitory.saveAll(books)
    }
}