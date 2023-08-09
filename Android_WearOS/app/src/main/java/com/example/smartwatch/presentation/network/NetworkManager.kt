package com.example.smartwatch.presentation.network

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.io.FileOutputStream

class NetworkManager(private val baseUrl: String) {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    private val fileService: FileService = retrofit.create(FileService::class.java)

    fun uploadDbFile(filePath: String, successCallback: () -> Unit, errorCallback: (Throwable) -> Unit) {
        val file = File(filePath)
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        fileService.uploadFile(body).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    successCallback.invoke()
                } else {
                    errorCallback.invoke(Throwable("Upload failed"))
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                errorCallback.invoke(t)
            }
        })
    }


    fun downloadDbFile(
        targetFilePath: String,
        successCallback: (Boolean) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        fileService.downloadFile().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val dbByteArray = responseBody.bytes()
                        val success = saveDbToFile(dbByteArray, targetFilePath)
                        successCallback.invoke(success)
                    } else {
                        errorCallback.invoke(Throwable("Download failed"))
                    }
                } else {
                    errorCallback.invoke(Throwable("Download failed"))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                errorCallback.invoke(t)
            }
        })
    }

    private fun saveDbToFile(dbByteArray: ByteArray, targetFilePath: String): Boolean {
        return try {
            val outputStream = FileOutputStream(targetFilePath)
            outputStream.write(dbByteArray)
            outputStream.close()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }



}