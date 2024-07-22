package com.futm.backend.model.dto

import com.futm.backend.model.TestResultStatus
import java.util.*

data class TestResultRequest (
    val status: TestResultStatus,
    val executedById: UUID,
    val testCaseId: UUID,
    val testRunId: UUID
)