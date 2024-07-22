package com.futm.backend.model.dto

import java.util.*

data class TestCaseRequest(
    val title: String,
    val description: String,
    val expectedResult: String,
    val ownerId: UUID,
    val projectId: UUID,
    val requirementId: UUID,
    val steps: List<String>
)
