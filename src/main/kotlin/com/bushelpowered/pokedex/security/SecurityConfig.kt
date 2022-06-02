package com.bushelpowered.pokedex.security

import com.bushelpowered.pokedex.repositories.AppUserRepository
import org.apache.catalina.filters.CorsFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.savedrequest.NullRequestCache
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class SecurityConfig(
    val userRepo : AppUserRepository
) : WebSecurityConfigurerAdapter() {

    val authenticationEntryPoint = RESTAuthenticationEntryPoint()

//    override fun configure(auth : AuthenticationManagerBuilder){
//        auth.userDetailsService {
//            username : String ->
//            userRepo.findByUsern ame(username)
//    }


    override fun configure(http: HttpSecurity) {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .requestCache().requestCache(NullRequestCache()).and()
            .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .csrf().disable()
            .cors()

        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/pokemon").permitAll()
            .antMatchers(HttpMethod.GET, "/api/trainers").hasAnyRole("ADMIN","PROFESSOR")
            .antMatchers(HttpMethod.POST, "/api/trainers").hasAnyRole("ADMIN","PROFESSOR")
            .antMatchers(HttpMethod.DELETE, "/api/trainers/**").hasAnyRole("ADMIN")

        http.formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .permitAll()
            .and()
            .httpBasic()
//        http.addFilterBefore(
//            jwtTokenFilter,
//            UsernamePasswordAuthenticationFilter::class.java
//        )
    }

//    @Bean
//    fun passwordEncoder() :PasswordEncoder {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
//    }



}