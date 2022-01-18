package br.com.cursospringkotlinudemy.controller.response

import br.com.cursospringkotlinudemy.CustomerStatus

data class CustomerResponse(
    val id: Int? = null,
    val name: String,
    val email: String,
    val status: CustomerStatus
)