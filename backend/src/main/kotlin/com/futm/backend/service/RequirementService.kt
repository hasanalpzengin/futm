package com.futm.backend.service

import com.futm.backend.model.Requirement
import com.futm.backend.model.RequirementStatus
import com.futm.backend.repository.ProjectRepository
import com.futm.backend.repository.RequirementRepository
import com.futm.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class RequirementService(
    @Autowired private val requirementRepository: RequirementRepository,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val projectRepository: ProjectRepository
) {

    fun createRequirement(title: String, description: String, status: RequirementStatus, ownerId: UUID, projectId: UUID): Requirement {
        val owner = userRepository.findById(ownerId).orElseThrow { IllegalArgumentException("User not found") }
        val project = projectRepository.findById(projectId).orElseThrow { IllegalArgumentException("Project not found") }
        val requirement = Requirement(title = title, description = description, status = status, owner = owner, project = project)
        return requirementRepository.save(requirement)
    }

    fun getRequirementById(id: UUID): Requirement? {
        return requirementRepository.findById(id).orElse(null)
    }

    fun getAllRequirements(): List<Requirement> {
        return requirementRepository.findAll()
    }

    fun updateRequirement(id: UUID, title: String, description: String, status: RequirementStatus, ownerId: UUID, projectId: UUID): Requirement? {
        val requirement = requirementRepository.findById(id).orElse(null)
        val owner = userRepository.findById(ownerId).orElseThrow { IllegalArgumentException("User not found") }
        val project = projectRepository.findById(projectId).orElseThrow { IllegalArgumentException("Project not found") }
        requirement?.let {
            it.title = title
            it.description = description
            it.status = status
            it.owner = owner
            it.project = project
            return requirementRepository.save(it)
        }
        return null
    }

    fun deleteRequirement(id: UUID) {
        requirementRepository.deleteById(id)
    }
}
