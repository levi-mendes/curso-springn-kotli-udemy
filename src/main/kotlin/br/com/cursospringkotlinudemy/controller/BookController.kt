package br.com.cursospringkotlinudemy.controller

import br.com.cursospringkotlinudemy.controller.request.PostBookRequest
import br.com.cursospringkotlinudemy.controller.request.PutBookRequest
import br.com.cursospringkotlinudemy.extension.toBookModel
import br.com.cursospringkotlinudemy.model.BookModel
import br.com.cursospringkotlinudemy.service.BookService
import br.com.cursospringkotlinudemy.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("book")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostBookRequest) {
        val customer = customerService.getCustomerId(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    fun getBooks() = bookService.getBooks()

    @GetMapping("/active")
    fun findActives() = bookService.findActives()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int) = bookService.findById(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Int) = bookService.deleteById(id)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody request: PutBookRequest) {
        val bookModel = findById(id)
        bookService.update(request.toBookModel(bookModel))
    }
}