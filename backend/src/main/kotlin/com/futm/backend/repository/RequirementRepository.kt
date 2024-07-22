package com.futm.backend.repository

import com.futm.backend.model.Requirement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RequirementRepository : JpaRepository<Requirement, UUID>