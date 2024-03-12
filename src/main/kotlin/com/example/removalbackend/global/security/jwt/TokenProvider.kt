package com.example.removalbackend.global.security.jwt

import com.example.removalbackend.domain.user.domain.RefreshToken
import com.example.removalbackend.domain.user.domain.repository.RefreshTokenRepository
import com.example.removalbackend.domain.user.presentation.dto.response.TokenResponse
import com.example.removalbackend.global.exception.TokenExpiredException
import com.example.removalbackend.global.exception.TokenInvalidException
import com.example.removalbackend.global.security.auth.AuthDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class TokenProvider(
    private val tokenProperties: TokenProperties,
    private val authDetailsService: AuthDetailsService,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun generateToken(accountId:String): TokenResponse{
        val accessToken: String = generateAccessToken(accountId)
        val refreshToken: String = generateRefreshToken(accountId)
        return TokenResponse(accessToken,refreshToken)
    }

    fun generateAccessToken(accountId: String):String{
        return createToken(accountId,"access", tokenProperties.accessExp)
    }
    fun generateRefreshToken(accountId: String): String {
        val refreshToken = createToken(accountId, "refresh", tokenProperties.refreshExp)
        refreshTokenRepository.save(RefreshToken(id = null, refreshToken))
        return refreshToken
    }

    private fun createToken(accountId: String, typ: String, exp: Long): String {
        return Jwts.builder()
            .setSubject(accountId)
            .claim("typ", typ)
            .signWith(SignatureAlgorithm.HS256, tokenProperties.secretKey)
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .setIssuedAt(Date(System.currentTimeMillis()))
            .compact()
    }
    fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
        println(getAccountId(token))
        val userDetails: UserDetails = authDetailsService.loadUserByUsername(getAccountId(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getAccountId(token: String): String {
        return getClaims(token).subject
    }

    private fun getClaims(token: String): Claims {
        return try {
            Jwts.parser()
                .setSigningKey(tokenProperties.secretKey)
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            throw TokenExpiredException
        } catch (e: Exception) {
            e.printStackTrace()
            throw TokenInvalidException
        }
    }

    fun resolveToken(request: HttpServletRequest): String? {

        val bearerToken = request.getHeader(tokenProperties.header)

        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenProperties.prefix)
            && bearerToken.length > tokenProperties.prefix.length + 1
        ) {
            bearerToken.substring(tokenProperties.prefix.length)
        } else null

    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(tokenProperties.secretKey).parseClaimsJws(token).body
            true
        } catch (e: Exception) {
            false
        }
    }
}