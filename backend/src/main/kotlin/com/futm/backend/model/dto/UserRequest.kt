package com.futm.backend.model.dto

import org.springframework.web.bind.annotation.RequestParam

data class UserRequest(
    val name: String,
    val email: String
)
