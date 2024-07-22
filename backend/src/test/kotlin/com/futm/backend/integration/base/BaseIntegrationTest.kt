package com.futm.backend.integration.base

import com.futm.backend.integration.config.TestConfig
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@ContextConfiguration(initializers = [TestConfig.Initializer::class])
abstract class BaseIntegrationTest