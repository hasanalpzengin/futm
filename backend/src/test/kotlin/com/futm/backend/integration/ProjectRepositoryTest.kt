package com.futm.backend.integration

import com.futm.backend.integration.base.BaseIntegrationTest
import com.futm.backend.model.Project
import com.futm.backend.model.User
import com.futm.backend.repository.ProjectRepository
import com.futm.backend.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import kotlin.test.AfterTest

class ProjectRepositoryTest : BaseIntegrationTest() {

    @Autowired
    lateinit var projectRepository: ProjectRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `should save and find project by id`() {
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
        val foundProject = projectRepository.findById(savedProject.id)

        assertTrue(foundProject.isPresent)
        assertEquals("Test Project", foundProject.get().name)
        assertEquals("Test Project Description", foundProject.get().description)
        assertEquals(savedUser.id, foundProject.get().owner.id)
    }

    @Test
    fun `should find all projects`() {
        val user = User(name = "John Doe", email = "john.doe@example.com")
        val savedUser = userRepository.save(user)
        val project1 = Project(
            name = "Test Project 1",
            description = "Test Project Description 1",
            startDate = LocalDateTime.now(),
            endDate = null,
            owner = savedUser
        )
        val project2 = Project(
            name = "Test Project 2",
            description = "Test Project Description 2",
            startDate = LocalDateTime.now(),
            endDate = null,
            owner = savedUser
        )
        projectRepository.save(project1)
        projectRepository.save(project2)

        val projects = projectRepository.findAll()
        assertEquals(2, projects.size)
    }

    @Test
    fun `should delete project by id`() {
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
        projectRepository.deleteById(savedProject.id)
        val foundProject = projectRepository.findById(savedProject.id)

        assertFalse(foundProject.isPresent)
    }

    @AfterTest
    fun `clean up`(){
        projectRepository.deleteAll()
        userRepository.deleteAll()
    }
}
