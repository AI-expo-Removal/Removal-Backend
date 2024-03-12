package com.example.leaveleave.global.error

import com.example.removalbackend.global.error.ErrorResponse
import com.example.removalbackend.global.error.exception.RemovalException
import com.example.removalbackend.global.exception.InternalServerError
import com.fasterxml.jackson.databind.ObjectMapper
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {
    @Throws(IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            e.printStackTrace()
            when (e) {
                is RemovalException -> writeErrorCode(e, response)
                else -> writeErrorCode(InternalServerError, response)
            }
        }
    }

    @Throws(IOException::class)
    private fun writeErrorCode(exception: RemovalException, response: HttpServletResponse) {
        val errorResponse = ErrorResponse.of(exception)

        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = errorResponse.status
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}