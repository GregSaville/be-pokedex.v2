package com.bushelpowered.pokedex.config

import com.bushelpowered.pokedex.adapter.persistence.repository.TrainerRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.savedrequest.NullRequestCache


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfig(private val trainerRepo: TrainerRepository) : WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .requestCache().requestCache(NullRequestCache()).and()
            .csrf().disable()
            .cors()
            .and().httpBasic()

        http.authorizeRequests()
            .antMatchers(HttpMethod.PUT, "/admin/loadCSV").hasAnyRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/trainers/**").hasAnyRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/api/trainers").permitAll()
            .antMatchers(HttpMethod.PUT, "/api/trainers/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/**")
            .permitAll()
            .anyRequest().authenticated()

    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
            .passwordEncoder(encoder())
            .withUser("admin")
            .password(encoder().encode("admin"))
            .roles("ADMIN")
    }

    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}

