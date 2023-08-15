package com.example.smartwatch.presentation.login.api

import com.example.smartwatch.presentation.common.domain.ResponseDTO
import com.example.smartwatch.presentation.login.domain.CaptchaVO
import com.example.smartwatch.presentation.login.domain.LoginForm
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface LoginApi {
    @POST("/login/Watch")
    fun loginUser(@Body loginForm: LoginForm?): Call<ResponseDTO<String>>

    @GET("/login/getCaptcha")
    fun getCaptcha(): Call<ResponseDTO<CaptchaVO>>
}