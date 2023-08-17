package com.example.smartwatch.presentation.network

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileService {
    @Multipart
    @POST("/file/upload")
    fun uploadFile(
        @Header("x-access-token") token: String,
        @Part filePart: MultipartBody.Part
    ): Call<Void>

    @GET("/file/download")
    fun downloadFile(
        @Header("x-access-token") token: String
    ): Call<ResponseBody>


}