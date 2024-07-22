package com.futm.backend.model

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "test_results")
@EntityListeners(AuditingEntityListener::class)
data class TestResult(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_case_id", nullable = false)
    var testCase: TestCase,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: TestResultStatus = TestResultStatus.NOT_EXECUTED,

    var executionDate: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executed_by", nullable = true)
    var executedBy: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_run_id", nullable = false)
    var testRun: TestRun,

    @OneToMany(mappedBy = "testResult", cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: List<Comment> = listOf()
)