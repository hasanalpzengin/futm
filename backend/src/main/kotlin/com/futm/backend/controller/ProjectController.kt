package com.futm.backend.controller

import com.futm.backend.model.Project
import com.futm.backend.model.dto.ProjectRequest
import com.futm.backend.service.ProjectService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/projects")
class ProjectController(
    private val projectService: ProjectService
) {

    @PostMapping
    fun createProject(
        @RequestBody projectRequest: ProjectRequest
    ): ResponseEntity<Project> {
        val project = projectService.createProject(
            projectRequest.name,
            projectRequest.description,
            projectRequest.ownerId,
            projectRequest.startDate
        )
        return ResponseEntity.ok(project)
    }

    @GetMapping("/{id}")
    fun getProjectById(@PathVariable id: UUID): ResponseEntity<Project> {
        val project = projectService.getProjectById(id)
        return if (project != null) {
            ResponseEntity.ok(project)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getAllProjects(): ResponseEntity<List<Project>> {
        val projects = projectService.getAllProjects()
        return ResponseEntity.ok(projects)
    }

    @PutMapping("/{id}")
    fun updateProject(
        @PathVariable id: UUID,
        @RequestParam name: String,
        @RequestParam description: String,
        @RequestParam ownerId: UUID
    ): ResponseEntity<Project> {
        val project = projectService.updateProject(id, name, description, ownerId)
        return if (project != null) {
            ResponseEntity.ok(project)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteProject(@PathVariable id: UUID): ResponseEntity<Unit> {
        projectService.deleteProject(id)
        return ResponseEntity.noContent().build()
    }
}
