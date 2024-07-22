package com.futm.backend.repository

import com.futm.backend.model.TestRun
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TestRunRepository : JpaRepository<TestRun, UUID>