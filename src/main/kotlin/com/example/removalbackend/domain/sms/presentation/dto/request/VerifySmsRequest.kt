package com.example.removalbackend.domain.sms.presentation.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank

data class VerifySmsRequest(
    @field: NotBlank(message = "null, 공백, 띄어쓰기를 허용하지 않습니다.")
    val phoneNumber: String,

    @JsonProperty("randomNumber") val randomNumber: String
)