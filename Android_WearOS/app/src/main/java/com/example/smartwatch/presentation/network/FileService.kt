package com.example.smartwatch.presentation.network

import com.example.smartwatch.presentation.common.domain.ResponseDTO
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface FileService {
    @Multipart
    @POST("/smart-admin-api/file/upload/{userId}")
    fun uploadFile(
        @Header("x-access-token") token: String,
        @Path("userId") userId: Long,
        @Part filePart: MultipartBody.Part
    ): Call<ResponseDTO<String>>

    @GET("/smart-admin-api/file/watch/download")
    fun downloadFile(
        @Header("x-access-token") token: String
    ): Call<ResponseDTO<String>>


}