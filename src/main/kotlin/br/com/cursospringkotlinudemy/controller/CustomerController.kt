package br.com.cursospringkotlinudemy.controller

import br.com.cursospringkotlinudemy.controller.request.PostCustomerRequest
import br.com.cursospringkotlinudemy.controller.request.PutCustomerRequest
import br.com.cursospringkotlinudemy.extension.toCustomerModel
import br.com.cursospringkotlinudemy.model.CustomerModel
import br.com.cursospringkotlinudemy.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
class CustomerController(
    val customerService: CustomerService
) {

    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerModel> =
        customerService.getAll(name)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostCustomerRequest) =
        customerService.create(request.toCustomerModel())

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerModel =
        customerService.findById(id)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody request: PutCustomerRequest) {
        val customer = getCustomer(id)
        customerService.update(request.toCustomerModel(customer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) =
        customerService.delete(id)
}