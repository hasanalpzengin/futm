package com.futm.backend.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "test_cases")
@EntityListeners(AuditingEntityListener::class)
data class TestCase(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    var title: String,

    var description: String,

    @ElementCollection
    @CollectionTable(name = "test_steps", joinColumns = [JoinColumn(name = "test_case_id")])
    @Column(name = "step")
    var steps: List<String>,

    @Column(nullable = false)
    var expectedResult: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: TestCaseStatus = TestCaseStatus.DRAFT,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    var updatedDate: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    var owner: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requirement_id", nullable = false)
    var requirement: Requirement,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    var project: Project,

    @OneToMany(mappedBy = "testCase", cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: List<Comment> = listOf()
)