package com.futm.backend.controller

import com.futm.backend.model.Requirement
import com.futm.backend.model.RequirementStatus
import com.futm.backend.model.dto.RequirementRequest
import com.futm.backend.service.RequirementService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/requirements")
class RequirementController(
    private val requirementService: RequirementService
) {

    @PostMapping
    fun createRequirement(
        @RequestBody requirementRequest: RequirementRequest
    ): ResponseEntity<Requirement> {
        val requirement = requirementService.createRequirement(
            requirementRequest.title,
            requirementRequest.description,
            requirementRequest.status,
            requirementRequest.ownerId,
            requirementRequest.projectId
        )
        return ResponseEntity.ok(requirement)
    }

    @GetMapping("/{id}")
    fun getRequirementById(@PathVariable id: UUID): ResponseEntity<Requirement> {
        val requirement = requirementService.getRequirementById(id)
        return if (requirement != null) {
            ResponseEntity.ok(requirement)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getAllRequirements(): ResponseEntity<List<Requirement>> {
        val requirements = requirementService.getAllRequirements()
        return ResponseEntity.ok(requirements)
    }

    @PutMapping("/{id}")
    fun updateRequirement(
        @PathVariable id: UUID,
        @RequestParam title: String,
        @RequestParam description: String,
        @RequestParam status: RequirementStatus,
        @RequestParam ownerId: UUID,
        @RequestParam projectId: UUID
    ): ResponseEntity<Requirement> {
        val requirement = requirementService.updateRequirement(id, title, description, status, ownerId, projectId)
        return if (requirement != null) {
            ResponseEntity.ok(requirement)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteRequirement(@PathVariable id: UUID): ResponseEntity<Unit> {
        requirementService.deleteRequirement(id)
        return ResponseEntity.noContent().build()
    }
}
