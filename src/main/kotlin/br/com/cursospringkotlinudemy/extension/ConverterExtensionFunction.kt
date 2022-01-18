package br.com.cursospringkotlinudemy.extension

import br.com.cursospringkotlinudemy.BookStatus
import br.com.cursospringkotlinudemy.CustomerStatus
import br.com.cursospringkotlinudemy.controller.request.PostBookRequest
import br.com.cursospringkotlinudemy.controller.request.PostCustomerRequest
import br.com.cursospringkotlinudemy.controller.request.PutBookRequest
import br.com.cursospringkotlinudemy.controller.request.PutCustomerRequest
import br.com.cursospringkotlinudemy.model.BookModel
import br.com.cursospringkotlinudemy.model.CustomerModel

fun PostCustomerRequest.toCustomerModel() =
    CustomerModel(name = this.name, email =  this.email, status = CustomerStatus.ATIVO)


fun PutCustomerRequest.toCustomerModel(customer: CustomerModel) =
    CustomerModel(
        id = customer.id,
        name = this.name,
        email =  this.email,
        status = customer.status
    )

fun PostBookRequest.toBookModel(customerModel: CustomerModel) =
    BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customerModel
    )

fun PutBookRequest.toBookModel(bookModel: BookModel) =
    BookModel(
        id = bookModel.id,
        name = this.name ?: bookModel.name,
        price = this.price ?: bookModel.price,
        status = bookModel.status,
        customer = bookModel.customer
    )