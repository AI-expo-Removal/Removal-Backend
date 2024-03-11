package com.example.removalbackend.domain.user.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "tbl_user")
class User(
    @Id
    @Column(length = 15, nullable = false)
    var accountId : String,

    @Column(length = 20, nullable = false)
    var password: String,

    @Column(length = 5, nullable = false)
    var name: String,

    @Column(length = 12, nullable = false)
    var phoneNumber: String
){
    @JsonCreator
    protected constructor() : this("", "", "", "")

    fun updatePassword(password: String){
        this.password = password
    }

    fun updatePhoneNumber(phoneNumber: String){
        this.phoneNumber = phoneNumber
    }
}