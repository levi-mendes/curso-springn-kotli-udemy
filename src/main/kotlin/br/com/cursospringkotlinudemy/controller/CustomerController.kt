package br.com.cursospringkotlinudemy.controller

import br.com.cursospringkotlinudemy.controller.request.PostCustomerRequest
import br.com.cursospringkotlinudemy.controller.request.PutCustomerRequest
import br.com.cursospringkotlinudemy.controller.response.CustomerResponse
import br.com.cursospringkotlinudemy.extension.toCustomerModel
import br.com.cursospringkotlinudemy.extension.toResponse
import br.com.cursospringkotlinudemy.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("customers")
class CustomerController(
    val customerService: CustomerService
) {

    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerResponse> =
        customerService.getAll(name).map { it.toResponse() }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostCustomerRequest) =
        customerService.create(request.toCustomerModel())

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerResponse =
        customerService.findById(id).toResponse()

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody @Valid request: PutCustomerRequest) {
        val customer = customerService.findById(id)
        customerService.update(request.toCustomerModel(customer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }
}