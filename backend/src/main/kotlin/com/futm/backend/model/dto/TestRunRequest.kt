package com.futm.backend.model.dto

import java.util.*

data class TestRunRequest (
    val name: String,
    val description: String,
    val projectId: UUID,
    val createdByUserId: UUID
)