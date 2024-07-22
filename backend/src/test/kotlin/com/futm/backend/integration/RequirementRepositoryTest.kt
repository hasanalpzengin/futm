package com.futm.backend.integration

import com.futm.backend.integration.base.BaseIntegrationTest
import com.futm.backend.model.Project
import com.futm.backend.model.Requirement
import com.futm.backend.model.RequirementStatus
import com.futm.backend.model.User
import com.futm.backend.repository.ProjectRepository
import com.futm.backend.repository.RequirementRepository
import com.futm.backend.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import kotlin.test.AfterTest

class RequirementRepositoryTest : BaseIntegrationTest() {

    @Autowired
    lateinit var requirementRepository: RequirementRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var projectRepository: ProjectRepository

    @Test
    fun `should save and find requirement by id`() {
        val user = User(name = "John Doe", email = "john.doe@example.com")
        val savedUser = userRepository.save(user)

        val project = Project(
            name = "Test Project",
            description = "Test Project Description",
            startDate = LocalDateTime.now(),
            endDate = null,
            owner = savedUser
        )
        val savedProject = projectRepository.save(project)
        val status = RequirementStatus.OPEN
        val requirement = Requirement(
            title = "Test Requirement",
            description = "Test Requirement Description",
            status = status,
            owner = savedUser,
            project = savedProject
        )
        val savedRequirement = requirementRepository.save(requirement)
        val foundRequirement = requirementRepository.findById(savedRequirement.id)

        assertTrue(foundRequirement.isPresent)
        assertEquals("Test Requirement", foundRequirement.get().title)
        assertEquals("Test Requirement Description", foundRequirement.get().description)
        assertEquals(savedUser.id, foundRequirement.get().owner.id)
        assertEquals(status, foundRequirement.get().status)
    }

    @Test
    fun `should find all requirements`() {
        val user = User(name = "John Doe", email = "john.doe@example.com")
        val savedUser = userRepository.save(user)
        val project = Project(
            name = "Test Project",
            description = "Test Project Description",
            startDate = LocalDateTime.now(),
            endDate = null,
            owner = savedUser
        )
        val savedProject = projectRepository.save(project)
        val status = RequirementStatus.OPEN
        val requirement1 = Requirement(
            title = "Test Requirement 1",
            description = "Test Requirement Description 1",
            status = status,
            owner = savedUser,
            project = savedProject
        )
        val requirement2 = Requirement(
            title = "Test Requirement 2",
            description = "Test Requirement Description 2",
            status = status,
            owner = savedUser,
            project = savedProject
        )
        requirementRepository.save(requirement1)
        requirementRepository.save(requirement2)

        val requirements = requirementRepository.findAll()
        assertEquals(2, requirements.size)
    }

    @Test
    fun `should delete requirement by id`() {
        val user = User(name = "John Doe", email = "john.doe@example.com")
        val savedUser = userRepository.save(user)
        val project = Project(
            name = "Test Project",
            description = "Test Project Description",
            startDate = LocalDateTime.now(),
            endDate = null,
            owner = savedUser
        )
        val savedProject = projectRepository.save(project)
        val status = RequirementStatus.OPEN
        val requirement = Requirement(
            title = "Test Requirement",
            description = "Test Requirement Description",
            status = status,
            owner = savedUser,
            project = savedProject
        )
        val savedRequirement = requirementRepository.save(requirement)
        requirementRepository.deleteById(savedRequirement.id)
        val foundRequirement = requirementRepository.findById(savedRequirement.id)

        assertFalse(foundRequirement.isPresent)
    }

    @AfterTest
    fun `clean up`(){
        requirementRepository.deleteAll()
        projectRepository.deleteAll()
        userRepository.deleteAll()
    }
}
