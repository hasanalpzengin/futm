package com.futm.backend.repository

import com.futm.backend.model.TestResult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TestResultRepository : JpaRepository<TestResult, UUID>