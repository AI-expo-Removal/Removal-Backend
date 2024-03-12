package com.example.removalbackend.domain.user.domain

import com.fasterxml.jackson.annotation.JsonCreator
import javax.persistence.*

@Entity(name = "tbl_user")
class User(
    @Id
    @Column(columnDefinition = "CHAR(20)", nullable = false)
    var accountId : String,

    @Column(columnDefinition = "CHAR(60)", nullable = false)
    var password: String,

    @Column(columnDefinition = "CHAR(5)", nullable = false)
    var name: String,

    @Column(columnDefinition = "CHAR(12)", nullable = false)
    var phoneNumber: String
){
    @JsonCreator
    protected constructor() : this( accountId = "user123", password = "password123", name = "hahahaha", phoneNumber = "123456789")

    fun updatePassword(password: String){
        this.password = password
    }

    fun updatePhoneNumber(phoneNumber: String){
        this.phoneNumber = phoneNumber
    }
}