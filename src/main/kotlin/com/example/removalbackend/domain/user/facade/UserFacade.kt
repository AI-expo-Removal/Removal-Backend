package com.example.removalbackend.domain.user.facade

import com.example.removalbackend.domain.user.domain.User
import com.example.removalbackend.domain.user.domain.repository.UserRepository
import com.example.removalbackend.domain.user.exception.UserNotFoundException
import org.springframework.security.core.context.SecurityContextHolder

class UserFacade(
    private val userRepository: UserRepository
) {
    fun getCurrentUser(): User{
        val accountId: String = SecurityContextHolder.getContext().authentication.name
        return getByAccountId(accountId)
    }
    fun checkAccountIdExist(accountId: String): Boolean {
        return userRepository.existsByAccountId(accountId)
    }
    fun getByAccountId(accountId: String): User{
        return userRepository.findByAccountId(accountId) ?: throw UserNotFoundException
    }
}