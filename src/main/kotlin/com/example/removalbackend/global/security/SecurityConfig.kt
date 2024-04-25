//package com.example.removalbackend.global.security
//
//import com.example.leaveleave.global.config.FilterConfig
//import com.example.removalbackend.global.security.jwt.TokenProvider
//import com.fasterxml.jackson.databind.ObjectMapper
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.security.web.SecurityFilterChain
//
//@Configuration
//@EnableWebSecurity
//class SecurityConfig(
//    private val objectMapper: ObjectMapper,
//    private val tokenProvider: TokenProvider
//) {
//
//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        return http
//            .csrf().disable()
//            .formLogin().disable()
//            .cors()
//
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//            .and()
//            .authorizeHttpRequests()
//            .antMatchers("/**").permitAll()
//            .anyRequest().permitAll()
//
//            .and().apply(FilterConfig(objectMapper , tokenProvider))
//            .and().build()
//    }
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
//}