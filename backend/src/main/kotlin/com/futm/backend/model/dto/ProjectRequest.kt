package com.futm.backend.model.dto

import java.time.LocalDateTime
import java.util.*

data class ProjectRequest(
    val name: String,
    val description: String,
    val ownerId: UUID?,
    val startDate: LocalDateTime
)