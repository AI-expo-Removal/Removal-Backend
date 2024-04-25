//package com.example.removalbackend.domain.user.domain
//
//import com.fasterxml.jackson.annotation.JsonCreator
//import javax.persistence.*
//
//@Entity(name = "tbl_user")
//class User(
//    @Id
//    @Column(name = "account_id", columnDefinition = "CHAR(20)", nullable = false)
//    var accountId: String,
//
//    @Column(name = "password", columnDefinition = "CHAR(60)", nullable = false)
//    var password: String,
//
//    @Column(name = "name", columnDefinition = "CHAR(5)", nullable = false)
//    var name: String,
//
////    @Column(name = "phone_number",columnDefinition = "CHAR(12)", nullable = false)
////    var phoneNumber: String
//) {
//    @JsonCreator
//    constructor() : this(
//        accountId = "user123",
//        password = "password123",
//        name = "hahahaha",
////        phoneNumber = "123456789"
//    )
//
//    @JsonCreator
//    fun updatePassword(password: String) {
//        this.password = password
//    }
//}