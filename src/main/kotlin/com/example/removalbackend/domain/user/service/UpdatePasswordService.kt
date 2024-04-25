//package com.example.removalbackend.domain.user.service
//
//import com.example.removalbackend.domain.user.domain.repository.UserRepository
//import com.example.removalbackend.domain.user.facade.UserFacade
//import com.example.removalbackend.domain.user.presentation.dto.request.UpdatePasswordRequest
//import org.springframework.stereotype.Service
//
//@Service
//class UpdatePasswordService(
//    private val userRepository: UserRepository,
//    private val userFacade: UserFacade
//) {
//    fun updatePassword(request: UpdatePasswordRequest) {
//        val user = userFacade.getCurrentUser()
//        user.updatePassword(request.password)
//
//        userRepository.save(user)
//    }
//}