package com.futm.backend.controller

import com.futm.backend.model.TestRun
import com.futm.backend.model.dto.TestRunRequest
import com.futm.backend.service.TestRunService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/test-runs")
class TestRunController(
    private val testRunService: TestRunService
) {

    @PostMapping
    fun createTestRun(
        @RequestBody testRunRequest: TestRunRequest
    ): ResponseEntity<TestRun> {
        val testRun = testRunService.createTestRun(testRunRequest.name, testRunRequest.description, testRunRequest.projectId, testRunRequest.createdByUserId)
        return ResponseEntity.ok(testRun)
    }

    @GetMapping("/{id}")
    fun getTestRunById(@PathVariable id: UUID): ResponseEntity<TestRun> {
        val testRun = testRunService.getTestRunById(id)
        return if (testRun != null) {
            ResponseEntity.ok(testRun)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getAllTestRuns(): ResponseEntity<List<TestRun>> {
        val testRuns = testRunService.getAllTestRuns()
        return ResponseEntity.ok(testRuns)
    }

    @PutMapping("/{id}")
    fun updateTestRun(
        @PathVariable id: UUID,
        @RequestParam name: String,
        @RequestParam description: String,
        @RequestParam projectId: UUID,
        @RequestParam ownerId: UUID
    ): ResponseEntity<TestRun> {
        val testRun = testRunService.updateTestRun(id, name, description, projectId, ownerId)
        return if (testRun != null) {
            ResponseEntity.ok(testRun)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTestRun(@PathVariable id: UUID): ResponseEntity<Unit> {
        testRunService.deleteTestRun(id)
        return ResponseEntity.noContent().build()
    }
}
