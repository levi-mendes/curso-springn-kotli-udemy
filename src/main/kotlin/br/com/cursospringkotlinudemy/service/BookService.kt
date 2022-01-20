package br.com.cursospringkotlinudemy.service

import br.com.cursospringkotlinudemy.enums.BookStatus
import br.com.cursospringkotlinudemy.enums.Errors
import br.com.cursospringkotlinudemy.exception.NotFoundException
import br.com.cursospringkotlinudemy.model.BookModel
import br.com.cursospringkotlinudemy.model.CustomerModel
import br.com.cursospringkotlinudemy.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class BookService(
    val repoitory: BookRepository
) {

    fun getBooks(pageable: Pageable): Page<BookModel> {
        return repoitory.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<BookModel> {
        return repoitory.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun create(book: BookModel) {
        repoitory.save(book)
    }

    fun deleteById(id: Int) {
        val book = findById(id)

        book.status = BookStatus.CANCELADO

        update(book)
    }

    fun findById(id: Int): BookModel {
        return repoitory.findById(id).orElseThrow{
            NotFoundException(
                Errors.ML0001.message.format(id), Errors.ML0001.code)
        }
    }

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