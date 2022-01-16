package br.com.cursospringkotlinudemy.service

import br.com.cursospringkotlinudemy.controller.request.PostCustomerRequest
import br.com.cursospringkotlinudemy.controller.request.PutCustomerRequest
import br.com.cursospringkotlinudemy.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {

    var customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter { it.name.contains(name, ignoreCase = true) }
        }
        return customers
    }

    fun id(): String {
        return if (customers.isEmpty()) {
            1
        } else {
            customers.last().id.toInt().plus(1)
        }.toString()
    }

    fun update(id: String, customer: PutCustomerRequest) {
        customers.first { it.id == id }.let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun getCustomer(id: String): CustomerModel {
        return customers.first { it.id == id }
    }

    fun create(customer: PostCustomerRequest) {
        customers.add(CustomerModel(id(), customer.name, customer.email))
    }

    fun delete(id: String) {
        customers.removeIf { it.id == id }
    }
}