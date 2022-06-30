package com.bushelpowered.pokedex.core.domain.jwt

import com.bushelpowered.pokedex.core.service.jwt.JwtUserDetailsService
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter(private val jwtUserDetailsService: JwtUserDetailsService,
                private val tokenManager: TokenManager
                ): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val tokenHeader = request.getHeader("Authorization")
        var username : String? = null
        var token: String? = null
        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")){
            token = tokenHeader.substring(7)
            try{
                username = tokenManager.getUsernameFromToken(token)
            } catch(e : java.lang.IllegalArgumentException){
                println("Unable to get JWT Token")
            } catch (e: ExpiredJwtException){
                println("JWT Token has expired")
            }
        } else {
            println(" Bearer String not found in token")
        }
        if(username != null && SecurityContextHolder.getContext().authentication == null){
            val userDetails = jwtUserDetailsService.loadUserByUsername(username)
            if(tokenManager.validateJwtToken(token!!, userDetails)){
                val authToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities)
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }
        filterChain.doFilter(request, response)
    }

}