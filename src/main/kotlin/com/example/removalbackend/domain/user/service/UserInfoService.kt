//package com.example.removalbackend.domain.user.service
//
//import com.example.removalbackend.domain.user.facade.UserFacade
//import com.example.removalbackend.domain.user.presentation.dto.response.UserInfoResponse
//import org.springframework.stereotype.Service
//
//@Service
//class UserInfoService(
//    private val userFacade: UserFacade
//) {
//    fun execute(): UserInfoResponse {
//        val user = userFacade.getCurrentUser()
//        return UserInfoResponse(user.accountId, user.name)
//    }
//}