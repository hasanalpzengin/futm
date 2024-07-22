package com.futm.backend.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "requirements")
@EntityListeners(AuditingEntityListener::class)
data class Requirement(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    var title: String,

    var description: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: RequirementStatus = RequirementStatus.OPEN,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    var updatedDate: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    var owner: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    var project: Project,

    @OneToMany(mappedBy = "requirement", cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: List<Comment> = listOf()
)