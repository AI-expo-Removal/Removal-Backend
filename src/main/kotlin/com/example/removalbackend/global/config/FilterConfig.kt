//package com.example.leaveleave.global.config
//
//import com.example.leaveleave.global.error.ExceptionFilter
//import com.example.removalbackend.global.security.jwt.TokenFilter
//import com.example.removalbackend.global.security.jwt.TokenProvider
//import com.fasterxml.jackson.databind.ObjectMapper
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.web.DefaultSecurityFilterChain
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
//
//class FilterConfig(
//    private val objectMapper: ObjectMapper,
//    private val tokenProvider: TokenProvider
//) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
//    override fun configure(builder: HttpSecurity?) {
//        val exceptionFilter = ExceptionFilter(objectMapper)
//        val tokenFilter = TokenFilter(tokenProvider)
//        builder?.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter::class.java)
//        builder?.addFilterBefore(exceptionFilter, TokenFilter::class.java)
//    }
//}