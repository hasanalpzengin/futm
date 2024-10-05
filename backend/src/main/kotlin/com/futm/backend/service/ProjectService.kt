package com.futm.backend.service

import com.futm.backend.model.Project
import com.futm.backend.model.User
import com.futm.backend.repository.ProjectRepository
import com.futm.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ProjectService(
    @Autowired private val projectRepository: ProjectRepository,
    @Autowired private val userRepository: UserRepository
) {

    fun createProject(name: String, description: String, ownerId: UUID?, startDate: LocalDateTime): Project {
        var owner: User? = null
        if(ownerId != null) {
            owner = userRepository.findById(ownerId).orElseThrow { IllegalArgumentException("User not found") }
        }
        val project = Project(name = name, description = description, owner = owner, startDate = startDate)
        return projectRepository.save(project)
    }

    fun getProjectById(id: UUID): Project? {
        return projectRepository.findById(id).orElse(null)
    }

    fun getAllProjects(): List<Project> {
        return projectRepository.findAll()
    }

    fun updateProject(id: UUID, name: String, description: String, ownerId: UUID?): Project? {
        val project = projectRepository.findById(id).orElse(null)
        var owner: User? = null
        if(ownerId != null) {
            owner = userRepository.findById(ownerId).orElseThrow { IllegalArgumentException("User not found") }
        }
        project?.let {
            it.name = name
            it.description = description
            it.owner = owner
            return projectRepository.save(it)
        }
        return null
    }

    fun deleteProject(id: UUID) {
        projectRepository.deleteById(id)
    }
}
