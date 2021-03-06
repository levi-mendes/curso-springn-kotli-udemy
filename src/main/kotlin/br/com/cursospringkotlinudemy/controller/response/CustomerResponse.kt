package br.com.cursospringkotlinudemy.controller.response

import br.com.cursospringkotlinudemy.enums.CustomerStatus

data class CustomerResponse(
    val id: Int? = null,
    val name: String,
    val email: String,
    val status: CustomerStatus
)