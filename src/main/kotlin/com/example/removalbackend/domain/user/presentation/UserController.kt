package com.example.removalbackend.domain.user.presentation

import com.example.removalbackend.domain.user.presentation.dto.request.SignInRequest
import com.example.removalbackend.domain.user.presentation.dto.request.SignUpRequest
import com.example.removalbackend.domain.user.presentation.dto.request.UpdatePasswordRequest
import com.example.removalbackend.domain.user.presentation.dto.response.TokenResponse
import com.example.removalbackend.domain.user.presentation.dto.response.UserInfoResponse
import com.example.removalbackend.domain.user.service.*
import com.example.removalbackend.global.security.jwt.TokenProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/users")
@RestController
class UserController(
    private val signInService: SignInService,
    private val signUpService: SignUpService,
    private val tokenProvider: TokenProvider,
    private val userInfoService: UserInfoService,
    private val updatePasswordService: UpdatePasswordService,
    private val withdrawalService: WithdrawalService

) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid request: SignUpRequest) {
        signUpService.execute(request)
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    fun signIn(@RequestBody @Valid request: SignInRequest): TokenResponse {
        return signInService.execute(request)
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/token")
    fun refreshToken(@RequestHeader("Authorization") authorization: String): ResponseEntity<Any> {
        val token = authorization.removePrefix("Bearer").trim()

        return if (tokenProvider.validateToken(token)) {
            val newToken = tokenProvider.generateToken(token)
            ResponseEntity.ok().body(mapOf("token" to newToken))
        } else {
            ResponseEntity.badRequest().body("Invalid token")
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user")
    fun userInfo(): UserInfoResponse {
        return userInfoService.execute()
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/password")
    fun updatePassword(@RequestBody @Valid request: UpdatePasswordRequest) {
        return updatePasswordService.updatePassword(request)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping()
    fun deleteMember() {
        return withdrawalService.memberDelete()
    }
}