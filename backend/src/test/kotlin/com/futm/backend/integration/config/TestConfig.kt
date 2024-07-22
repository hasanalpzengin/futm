package com.futm.backend.integration.config

import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.testcontainers.containers.PostgreSQLContainer
import javax.sql.DataSource

@TestConfiguration
class TestConfig {

    var logger = LoggerFactory.getLogger(TestConfig::class.java)

    companion object {
        val postgresqlContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:13.3").apply {
            withDatabaseName("test")
            withUsername("postgres")
            withPassword("password")
        }
    }

    @Bean
    fun dataSource(): DataSource {
        return DriverManagerDataSource().apply {
            setDriverClassName("org.postgresql.Driver")
            url = postgresqlContainer.jdbcUrl
            username = postgresqlContainer.username
            password = postgresqlContainer.password
        }
    }

    class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            postgresqlContainer.start()
            TestPropertyValues.of(
                "spring.datasource.url=${postgresqlContainer.jdbcUrl}",
                "spring.datasource.username=${postgresqlContainer.username}",
                "spring.datasource.password=${postgresqlContainer.password}"
            ).applyTo(applicationContext.environment)
        }
    }
}