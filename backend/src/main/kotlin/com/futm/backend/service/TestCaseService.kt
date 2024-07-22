package com.futm.backend.service

import com.futm.backend.model.Requirement
import com.futm.backend.model.TestCase
import com.futm.backend.model.TestCaseStatus
import com.futm.backend.repository.ProjectRepository
import com.futm.backend.repository.RequirementRepository
import com.futm.backend.repository.TestCaseRepository
import com.futm.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TestCaseService(
    @Autowired private val testCaseRepository: TestCaseRepository,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val projectRepository: ProjectRepository,
    @Autowired private val requirementRepository: RequirementRepository
) {

    fun createTestCase(
        title: String,
        description: String,
        expectedResult: String,
        ownerId: UUID,
        projectId: UUID,
        requirementId: UUID,
        steps: List<String>
    ): TestCase {
        val owner = userRepository.findById(ownerId).orElseThrow { IllegalArgumentException("User not found") }
        val project =
            projectRepository.findById(projectId).orElseThrow { IllegalArgumentException("Project not found") }
        val requirement =
            requirementRepository.findById(requirementId).orElseThrow { IllegalArgumentException("Project not found") }
        val testCase = TestCase(
            title = title,
            description = description,
            expectedResult = expectedResult,
            status = TestCaseStatus.DRAFT,
            owner = owner,
            project = project,
            requirement = requirement,
            steps = steps
        )
        return testCaseRepository.save(testCase)
    }

    fun getTestCaseById(id: UUID): TestCase? {
        return testCaseRepository.findById(id).orElse(null)
    }

    fun getAllTestCases(): List<TestCase> {
        return testCaseRepository.findAll()
    }

    fun updateTestCase(id: UUID, title: String, description: String, ownerId: UUID): TestCase? {
        val testCase = testCaseRepository.findById(id).orElse(null)
        val owner = userRepository.findById(ownerId).orElseThrow { IllegalArgumentException("User not found") }
        testCase?.let {
            it.title = title
            it.description = description
            it.owner = owner
            return testCaseRepository.save(it)
        }
        return null
    }

    fun deleteTestCase(id: UUID) {
        testCaseRepository.deleteById(id)
    }
}
