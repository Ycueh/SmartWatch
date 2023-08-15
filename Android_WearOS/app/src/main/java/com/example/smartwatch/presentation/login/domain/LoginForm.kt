package com.example.smartwatch.presentation.login.domain

data class LoginForm(
    val loginName: String,
    val password: String,
    val captchaUuid: String,
    val captchaCode: String
)
