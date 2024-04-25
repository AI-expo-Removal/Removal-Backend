//package com.example.removalbackend.domain.user.service
//
//import com.example.removalbackend.domain.user.domain.repository.UserRepository
//import com.example.removalbackend.domain.user.facade.UserFacade
//import org.springframework.stereotype.Service
//
//@Service
//class WithdrawalService(
//    private val userRepository: UserRepository,
//    private val userFacade: UserFacade
//) {
//    fun memberDelete() {
//        val user = userFacade.getCurrentUser()
//        return userRepository.delete(user)
//    }
//}