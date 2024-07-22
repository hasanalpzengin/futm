package com.futm.backend.controller

import com.futm.backend.model.TestResult
import com.futm.backend.model.TestResultStatus
import com.futm.backend.model.dto.TestResultRequest
import com.futm.backend.service.TestResultService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/test-results")
class TestResultController(
    private val testResultService: TestResultService
) {

    @PostMapping
    fun createTestResult(
        @RequestBody testResultRequest: TestResultRequest
    ): ResponseEntity<TestResult> {
        val testResult = testResultService.createTestResult(testResultRequest.status, testResultRequest.executedById, testResultRequest.testCaseId, testResultRequest.testRunId)
        return ResponseEntity.ok(testResult)
    }

    @GetMapping("/{id}")
    fun getTestResultById(@PathVariable id: UUID): ResponseEntity<TestResult> {
        val testResult = testResultService.getTestResultById(id)
        return if (testResult != null) {
            ResponseEntity.ok(testResult)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getAllTestResults(): ResponseEntity<List<TestResult>> {
        val testResults = testResultService.getAllTestResults()
        return ResponseEntity.ok(testResults)
    }

    @PutMapping("/{id}")
    fun updateTestResult(
        @PathVariable id: UUID,
        @RequestParam status: TestResultStatus,
        @RequestParam executedById: UUID,
        @RequestParam testRunId: UUID
    ): ResponseEntity<TestResult> {
        val testResult = testResultService.updateTestResult(id, status, executedById, testRunId)
        return if (testResult != null) {
            ResponseEntity.ok(testResult)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTestResult(@PathVariable id: UUID): ResponseEntity<Unit> {
        testResultService.deleteTestResult(id)
        return ResponseEntity.noContent().build()
    }
}
