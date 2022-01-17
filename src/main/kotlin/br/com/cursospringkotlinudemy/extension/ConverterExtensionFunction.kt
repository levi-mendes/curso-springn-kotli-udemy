package br.com.cursospringkotlinudemy.extension

import br.com.cursospringkotlinudemy.controller.request.PostCustomerRequest
import br.com.cursospringkotlinudemy.controller.request.PutCustomerRequest
import br.com.cursospringkotlinudemy.model.CustomerModel

fun PostCustomerRequest.toCustomerModel() =
    CustomerModel(name = this.name, email =  this.email)


fun PutCustomerRequest.toCustomerModel(id: String) =
    CustomerModel(id = id, name = this.name, email =  this.email)