package com.futm.backend.service

import com.futm.backend.model.TestRun
import com.futm.backend.repository.ProjectRepository
import com.futm.backend.repository.TestRunRepository
import com.futm.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TestRunService(
    @Autowired private val testRunRepository: TestRunRepository,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val projectRepository: ProjectRepository
) {

    fun createTestRun(name: String, description: String, projectId: UUID, createdByUserId: UUID): TestRun {
        val project = projectRepository.findById(projectId).orElseThrow { IllegalArgumentException("Project not found") }
        val createdByUser = userRepository.findById(createdByUserId).orElseThrow { IllegalArgumentException("User not found") }
        val testRun = TestRun(name = name, description = description, project = project, owner = createdByUser)
        return testRunRepository.save(testRun)
    }

    fun getTestRunById(id: UUID): TestRun? {
        return testRunRepository.findById(id).orElse(null)
    }

    fun getAllTestRuns(): List<TestRun> {
        return testRunRepository.findAll()
    }

    fun updateTestRun(id: UUID, name: String, description: String, projectId: UUID, ownerId: UUID): TestRun? {
        val testRun = testRunRepository.findById(id).orElse(null)
        val project = projectRepository.findById(projectId).orElseThrow { IllegalArgumentException("Project not found") }
        val owner = userRepository.findById(ownerId).orElseThrow { IllegalArgumentException("User not found") }
        testRun?.let {
            it.name = name
            it.description = description
            it.project = project
            it.owner = owner
            return testRunRepository.save(it)
        }
        return null
    }

    fun deleteTestRun(id: UUID) {
        testRunRepository.deleteById(id)
    }
}
