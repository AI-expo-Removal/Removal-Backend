package com.example.removalbackend.domain.sms.presentation.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class SmsRequest(
    @JsonProperty("phoneNumber") val phoneNumber: String
)
