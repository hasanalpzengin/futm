package com.futm.backend.model

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "test_runs")
@EntityListeners(AuditingEntityListener::class)
data class TestRun(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    var name: String,

    var description: String,

    @Column(nullable = false)
    var startDate: LocalDateTime = LocalDateTime.now(),

    var endDate: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    var owner: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    var project: Project,

    @OneToMany(mappedBy = "testRun", cascade = [CascadeType.ALL], orphanRemoval = true)
    var testResults: List<TestResult> = listOf()
)