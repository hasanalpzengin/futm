package com.futm.backend.controller

import com.futm.backend.model.Requirement
import com.futm.backend.model.TestCase
import com.futm.backend.model.dto.TestCaseRequest
import com.futm.backend.service.RequirementService
import com.futm.backend.service.TestCaseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.naming.directory.InvalidAttributesException

@RestController
@RequestMapping("/test-cases")
class TestCaseController(
    private val testCaseService: TestCaseService,
    private val requirementService: RequirementService
) {

    @PostMapping
    fun createTestCase(
        @RequestBody testCaseRequest: TestCaseRequest
    ): ResponseEntity<TestCase> {
        val testCase = testCaseService.createTestCase(
            testCaseRequest.title,
            testCaseRequest.description,
            testCaseRequest.expectedResult,
            testCaseRequest.requirementId,
            testCaseRequest.ownerId,
            testCaseRequest.projectId,
            testCaseRequest.steps
        )
        return ResponseEntity.ok(testCase)
    }

    @GetMapping("/{id}")
    fun getTestCaseById(@PathVariable id: UUID): ResponseEntity<TestCase> {
        val testCase = testCaseService.getTestCaseById(id)
        return if (testCase != null) {
            ResponseEntity.ok(testCase)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getAllTestCases(): ResponseEntity<List<TestCase>> {
        val testCases = testCaseService.getAllTestCases()
        return ResponseEntity.ok(testCases)
    }

    @PutMapping("/{id}")
    fun updateTestCase(
        @PathVariable id: UUID,
        @RequestParam title: String,
        @RequestParam description: String,
        @RequestParam ownerId: UUID
    ): ResponseEntity<TestCase> {
        val testCase = testCaseService.updateTestCase(id, title, description, ownerId)
        return if (testCase != null) {
            ResponseEntity.ok(testCase)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTestCase(@PathVariable id: UUID): ResponseEntity<Unit> {
        testCaseService.deleteTestCase(id)
        return ResponseEntity.noContent().build()
    }
}
