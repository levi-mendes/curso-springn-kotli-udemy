package br.com.cursospringkotlinudemy.controller.request

import br.com.cursospringkotlinudemy.enums.CustomerStatus

data class PutCustomerRequest(
    var name: String,
    var email: String,
    var status: CustomerStatus
)