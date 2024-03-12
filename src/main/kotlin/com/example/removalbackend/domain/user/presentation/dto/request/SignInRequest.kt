package com.example.removalbackend.domain.user.presentation.dto.request

import javax.validation.constraints.NotBlank

class SignInRequest(
    @field: NotBlank(message = "null, 공백, 띄어쓰기를 허용하지 않습니다.")
    val accountId: String,

    @field: NotBlank(message = "null, 공백, 띄어쓰기를 허용하지 않습니다.")
    val password: String,
)