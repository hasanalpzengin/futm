package com.futm.backend.service

import com.futm.backend.model.TestResult
import com.futm.backend.model.TestResultStatus
import com.futm.backend.repository.TestCaseRepository
import com.futm.backend.repository.TestResultRepository
import com.futm.backend.repository.TestRunRepository
import com.futm.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TestResultService(
    @Autowired private val testResultRepository: TestResultRepository,
    @Autowired private val testCaseRepository: TestCaseRepository,
    @Autowired private val testRunRepository: TestRunRepository,
    @Autowired private val userRepository: UserRepository
) {

    fun createTestResult(status: TestResultStatus, executedById: UUID, testCaseId: UUID, testRunId: UUID): TestResult {
        val executedBy = userRepository.findById(executedById).orElseThrow { IllegalArgumentException("User not found") }
        val testRun = testRunRepository.findById(testRunId).orElseThrow { IllegalArgumentException("Test run not found") }
        val testCase = testCaseRepository.findById(testCaseId).orElseThrow { IllegalArgumentException("Test case not found") }
        val testResult = TestResult(status = status, executedBy = executedBy, testRun = testRun, testCase = testCase)
        return testResultRepository.save(testResult)
    }

    fun getTestResultById(id: UUID): TestResult? {
        return testResultRepository.findById(id).orElse(null)
    }

    fun getAllTestResults(): List<TestResult> {
        return testResultRepository.findAll()
    }

    fun updateTestResult(id: UUID, status: TestResultStatus, executedById: UUID, testRunId: UUID): TestResult? {
        val testResult = testResultRepository.findById(id).orElse(null)
        val executedBy = userRepository.findById(executedById).orElseThrow { IllegalArgumentException("User not found") }
        val testRun = testRunRepository.findById(testRunId).orElseThrow { IllegalArgumentException("Test run not found") }
        testResult?.let {
            it.status = status
            it.executedBy = executedBy
            it.testRun = testRun
            return testResultRepository.save(it)
        }
        return null
    }

    fun deleteTestResult(id: UUID) {
        testResultRepository.deleteById(id)
    }
}
