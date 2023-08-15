package com.example.smartwatch.presentation.network

import android.util.Log
import com.example.smartwatch.presentation.common.domain.ResponseDTO
import com.example.smartwatch.presentation.login.api.LoginApi
import com.example.smartwatch.presentation.login.domain.CaptchaVO
import com.example.smartwatch.presentation.login.domain.LoginForm
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.io.FileOutputStream
import kotlin.math.log

class NetworkManager(private val baseUrl: String) {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val fileService: FileService = retrofit.create(FileService::class.java)
    private val loginApi:LoginApi = retrofit.create(LoginApi::class.java)

    fun login(
        loginName: String,
        password: String,
        successCallback: (String) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        fetchCaptcha(loginName, password, successCallback, errorCallback)
    }

    private fun fetchCaptcha(
        loginName: String,
        password: String,
        successCallback: (String) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        //Send restful request to back end
        loginApi.getCaptcha().enqueue(object : Callback<ResponseDTO<CaptchaVO>> {
            override fun onResponse(call: Call<ResponseDTO<CaptchaVO>>, response: Response<ResponseDTO<CaptchaVO>>) {
                //Get the response from back end
                if (response.isSuccessful) {
                    Log.d("Success Log",response.message())
                    response.body()?.data?.let {
                        //Login function
                        sendLoginRequest(loginName, password, it, successCallback, errorCallback)
                    } ?: errorCallback(Throwable("No captcha data received"))
                } else {
                    errorCallback(Throwable("Server response error: ${response.code()}:${response.message()} "))
                }
            }

            override fun onFailure(call: Call<ResponseDTO<CaptchaVO>>, t: Throwable) {
                errorCallback(t)
            }
        })
    }

    private fun sendLoginRequest(
        loginName: String,
        password: String,
        captchaVO: CaptchaVO,
        successCallback: (String) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        //Generate the form
        val loginForm = LoginForm(loginName, password, captchaVO.captchaUuid, captchaVO.captchaText)
        loginApi.loginUser(loginForm).enqueue(object : Callback<ResponseDTO<String>> {
            override fun onResponse(call: Call<ResponseDTO<String>>, loginResponse: Response<ResponseDTO<String>>) {
                if (loginResponse.isSuccessful) {
                    when (loginResponse.body()?.ok) {
                        true -> {
                            loginResponse.body()?.data?.let(successCallback)
                        }
                        false -> {
                            // Handle the case where ok is false
                            val errorMessage = loginResponse.body()?.msg ?: "Error"
                            errorCallback(Throwable(errorMessage))
                        }
                        else -> {
                            // Handle the case where ok or the body is null
                            errorCallback(Throwable("Login response body or ok flag is null"))
                        }
                    }
                } else {
                    errorCallback(Throwable("Login failed: ${loginResponse.code()}"))
                }
            }

            override fun onFailure(call: Call<ResponseDTO<String>>, t: Throwable) {
                errorCallback(t)
            }
        })
    }

    fun logOut(
        token:String,
        successCallback: (String) -> Unit,
        errorCallback: (Throwable) -> Unit
    ){
        loginApi.logOut(token).enqueue(object : Callback<ResponseDTO<String>> {
            override fun onResponse(call: Call<ResponseDTO<String>>, logoutResponse: Response<ResponseDTO<String>>) {
                if (logoutResponse.isSuccessful) {
                    when (logoutResponse.body()?.ok) {
                        true -> {
                            // Handle the case where ok is true
                            logoutResponse.body()?.msg?.let { message ->
                                successCallback(message)
                            } ?: run {
                                errorCallback(Throwable("Message is null"))
                            }
                        }
                        false -> {
                            // Handle the case where ok is false
                            val errorMessage = logoutResponse.body()?.msg ?: "Error"
                            errorCallback(Throwable(errorMessage))
                        }
                        else -> {
                            // Handle the case where ok or the body is null
                            errorCallback(Throwable("Login response body or ok flag is null"))
                        }
                    }
                } else {
                    errorCallback(Throwable("Login failed: ${logoutResponse.code()}"))
                }
            }

            override fun onFailure(call: Call<ResponseDTO<String>>, t: Throwable) {
                errorCallback(t)
            }
        })
    }

    fun uploadDbFile(filePath: String, token: String, successCallback: () -> Unit, errorCallback: (Throwable) -> Unit) {
        val file = File(filePath)
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        fileService.uploadFile(token, body).enqueue(object : Callback<Void> {
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
        token: String,
        successCallback: (Boolean) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        fileService.downloadFile(token).enqueue(object : Callback<ResponseBody> {
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