package com.futm.backend.model.dto

import com.futm.backend.model.RequirementStatus
import java.util.*

data class RequirementRequest(
    val title: String,
    val description: String,
    val status: RequirementStatus,
    val ownerId: UUID,
    val projectId: UUID
)