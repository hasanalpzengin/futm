package com.futm.backend.service

import com.futm.backend.model.User
import com.futm.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    @Autowired private val userRepository: UserRepository
) {

    fun createUser(name: String, email: String): User {
        val user = User(name = name, email = email)
        return userRepository.save(user)
    }

    fun getUserById(id: UUID): User? {
        return userRepository.findById(id).orElse(null)
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    fun updateUser(id: UUID, name: String, email: String): User? {
        val user = userRepository.findById(id).orElse(null)
        user?.let {
            it.name = name
            it.email = email
            return userRepository.save(it)
        }
        return null
    }

    fun deleteUser(id: UUID) {
        userRepository.deleteById(id)
    }
}
