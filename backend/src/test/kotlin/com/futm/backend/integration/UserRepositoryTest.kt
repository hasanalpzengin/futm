package com.futm.backend.integration

import com.futm.backend.integration.base.BaseIntegrationTest
import com.futm.backend.model.User
import com.futm.backend.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.AfterTest

class UserRepositoryTest : BaseIntegrationTest() {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `should save and find user by id`() {
        val user = User(name = "John Doe", email = "john.doe@example.com")
        val savedUser = userRepository.save(user)
        val foundUser = userRepository.findById(savedUser.id)

        assertTrue(foundUser.isPresent)
        assertEquals("John Doe", foundUser.get().name)
        assertEquals("john.doe@example.com", foundUser.get().email)
    }

    @Test
    fun `should find all users`() {
        val user1 = User(name = "John Doe", email = "john.doe@example.com")
        val user2 = User(name = "Jane Doe", email = "jane.doe@example.com")
        userRepository.save(user1)
        userRepository.save(user2)

        val users = userRepository.findAll()
        assertEquals(2, users.size)
    }

    @Test
    fun `should delete user by id`() {
        val user = User(name = "John Doe", email = "john.doe@example.com")
        val savedUser = userRepository.save(user)
        userRepository.deleteById(savedUser.id)
        val foundUser = userRepository.findById(savedUser.id)

        assertFalse(foundUser.isPresent)
    }

    @AfterTest
    fun `clean up`() {
        userRepository.deleteAll()
    }
}