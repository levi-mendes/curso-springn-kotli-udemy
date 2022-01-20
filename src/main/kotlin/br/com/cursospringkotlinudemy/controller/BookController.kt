package br.com.cursospringkotlinudemy.controller

import br.com.cursospringkotlinudemy.controller.request.PostBookRequest
import br.com.cursospringkotlinudemy.controller.request.PutBookRequest
import br.com.cursospringkotlinudemy.extension.toBookModel
import br.com.cursospringkotlinudemy.extension.toResponse
import br.com.cursospringkotlinudemy.service.BookService
import br.com.cursospringkotlinudemy.service.CustomerService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("book")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostBookRequest) {
        val customer = customerService.findById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    fun getBooks(@PageableDefault(page = 0, size = 10) pageable: Pageable) =
        bookService.getBooks(pageable).map { it.toResponse() }

    @GetMapping("/active")
    fun findActives(@PageableDefault(page = 0, size = 10) pageable: Pageable) =
        bookService.findActives(pageable).map { it.toResponse() }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int) = bookService.findById(id).toResponse()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Int) = bookService.deleteById(id)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody request: PutBookRequest) {
        val bookModel = bookService.findById(id)
        bookService.update(request.toBookModel(bookModel))
    }
}