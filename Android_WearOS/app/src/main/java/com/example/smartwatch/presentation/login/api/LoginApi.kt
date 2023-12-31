package com.example.smartwatch.presentation.login.api

import com.example.smartwatch.presentation.common.domain.ResponseDTO
import com.example.smartwatch.presentation.login.domain.CaptchaVO
import com.example.smartwatch.presentation.login.domain.LoginForm
import com.example.smartwatch.presentation.login.domain.LoginWatchDetail
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface LoginApi {
    @POST("/smart-admin-api/login/Watch")
    fun loginUser(@Body loginForm: LoginForm?): Call<ResponseDTO<LoginWatchDetail>>

    @GET("/smart-admin-api/login/logout")
    fun logOut(
        @Header("x-access-token") token: String
    ):  Call<ResponseDTO<String>>
}