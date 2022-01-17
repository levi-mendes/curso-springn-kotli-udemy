package br.com.cursospringkotlinudemy.service

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
            customers.last().id?.toInt()?.plus(1)
        }.toString()
    }

    fun update(customer: CustomerModel) {
        customers.first { it.id == customer.id }.let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun getCustomer(id: String): CustomerModel {
        return customers.first { it.id == id }
    }

    fun create(customer: CustomerModel) {
        customers.add(CustomerModel(id(), customer.name, customer.email))
    }

    fun delete(id: String) {
        customers.removeIf { it.id == id }
    }
}