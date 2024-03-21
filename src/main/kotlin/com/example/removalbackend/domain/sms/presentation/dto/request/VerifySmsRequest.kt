package com.example.removalbackend.domain.sms.presentation.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank

data class VerifySmsRequest(
    val phoneNumber: String,

    @JsonProperty("randomNumber")
    val randomNumber: String
)