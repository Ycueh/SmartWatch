package com.example.smartwatch.presentation.network

import android.util.Base64
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
        sendLoginRequest(loginName, password, successCallback, errorCallback)
    }


    private fun sendLoginRequest(
        loginName: String,
        password: String,
        successCallback: (String) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        //Generate the form
        val loginForm = LoginForm(loginName, password, "captchaVO.captchaUuid", "captchaVO.captchaText")
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

    fun uploadDbFile(filePath: String,
                     token: String,
                     successCallback: (String) -> Unit,
                     errorCallback: (Throwable,Int?) -> Unit) {
        val file = File(filePath)
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        fileService.uploadFile(token, body).enqueue(object : Callback<ResponseDTO<String>> {
            override fun onResponse(call: Call<ResponseDTO<String>>, response: Response<ResponseDTO<String>>) {
                if (response.isSuccessful) {
                    val code = response.body()?.code
                    when (response.body()?.ok) {
                        true -> {
                            // Handle the case where ok is true
                            response.body()?.msg?.let { message ->
                                successCallback(message)
                            } ?: run {
                                errorCallback(Throwable("Message is null"),code)
                            }
                        }
                        false -> {
                            val errorMessage = response.body()?.msg ?: "Upload failed"
                            val errorCode = response.body()?.code
                            errorCallback.invoke(Throwable(errorMessage), errorCode)
                        }
                        else -> {
                            // Handle the case where ok or the body is null
                            errorCallback(Throwable("Upload failed"),code)
                        }
                    }
                } else {
                    val code = response.body()?.code
                    val msg = response.body()?.msg
                    errorCallback(Throwable(msg),code)
                }
            }

            override fun onFailure(call: Call<ResponseDTO<String>>, t: Throwable) {
                errorCallback.invoke(t,null)
            }
        })
    }


    fun downloadDbFile(
        targetFilePath: String,
        token: String,
        successCallback: (Boolean) -> Unit,
        errorCallback: (Throwable, Int?) -> Unit
    ) {
        fileService.downloadFile(token).enqueue(object : Callback<ResponseDTO<String>> {
            override fun onResponse(call: Call<ResponseDTO<String>>, response: Response<ResponseDTO<String>>) {
                if (response.isSuccessful) {
                    val responseBodyDTO = response.body()
                    if (responseBodyDTO?.ok == true && responseBodyDTO.data != null) {
                        val dbByteArray = Base64.decode(responseBodyDTO.data, Base64.DEFAULT)
                        val success = saveDbToFile(dbByteArray, targetFilePath)
                        successCallback.invoke(success)
                    } else {
                        // Extract the message and code from the ResponseDTO
                        val errorMessage = responseBodyDTO?.msg ?: "Download failed"
                        val errorCode = responseBodyDTO?.code
                        errorCallback.invoke(Throwable(errorMessage), errorCode)
                    }
                } else {
                    errorCallback.invoke(Throwable(response.body()?.msg), response.body()?.code)
                }
            }

            override fun onFailure(call: Call<ResponseDTO<String>>, t: Throwable) {
                errorCallback.invoke(t, null)
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