/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.smartwatch.presentation


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.smartwatch.R
import com.example.smartwatch.presentation.network.NetworkManager
import com.example.smartwatch.presentation.theme.SmartWatchTheme
import java.io.File


class LoginActivity : Activity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
       var ip = "10.76.6.187"
//    var ip = "10.0.2.2"
    //var ip = "47.110.226.70"
    private val baseUrl = "http://$ip:1024/"
    private val networkManager = NetworkManager(baseUrl)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Initialize view reference
        usernameEditText = findViewById(R.id.AccountEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        // Set click listener
        loginButton.setOnClickListener {
            // perform login operation
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            networkManager.login(username, password,
                successCallback = { loginUserDetail ->
                    // jump to MainActivity
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("TOKEN", loginUserDetail.token)
                    intent.putExtra("USERID",loginUserDetail.userId)
                    startActivity(intent)
                    finish()  // close LoginActivity
                },
                errorCallback = { error ->
                    // show error message
                    Toast.makeText(this, error.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                 var ip = "10.76.6.187"
//            var ip = "10.0.2.2"
            //ipv4 address
//            var ip = "172.31.27.128"
            val baseUrl = "http://$ip:1024/"
            val networkManager = NetworkManager(baseUrl)
            val token = intent.getStringExtra("TOKEN")
            val userId = intent.getLongExtra("USERID", -1L)
            if (token == null||userId == -1L) {
                Toast.makeText(this, "No token or userId", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                //Upload Download function
            }else{
                WearApp(networkManager, token, userId)
            }
        }
    }
}

fun requestFileAccessPermission(context: Context) {
    val uri = Uri.fromParts("package", context.packageName, null)
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    intent.data = uri
    context.startActivity(intent)
}
//
@Composable
fun WearApp(networkManager: NetworkManager, token: String, userId:Long) {
    val context = LocalContext.current
    val uploadState = remember { mutableStateOf<UploadState?>(null) } // Record upload status
    val downloadState = remember { mutableStateOf<DownloadState?>(null) } // Record download status

//    val requestPermissionLauncher =
//        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
//            if (isGranted) {
//                bindUploadFile(networkManager) { state ->
//                    uploadState.value = state
//                }
//            } else {
//                Log.d("Permission", "Storage permission denied")
//            }
//        }

    SmartWatchTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && Environment.isExternalStorageManager()) {
                        // For API 30 and higher, the app has "All file access"
                        uploadState.value = null
                        bindUploadFile(networkManager, token, context, userId) { state ->
                            uploadState.value = state // update upload status
                            Log.d("testUpload", "$state")
                        }
                    } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R &&
                        ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        // For API 28 and 29, the app has the WRITE_EXTERNAL_STORAGE permission
                        uploadState.value = null
                        bindUploadFile(networkManager, token, context, userId) { state ->
                            uploadState.value = state // update upload status
                            Log.d("testUpload", "$state")
                        }
                    } else {
                        // if the app doesn't have the required permission, request it
                        requestFileAccessPermission(context)  // Call the function that requests permission
                    }
                },
                modifier = Modifier
//                    .fillMaxWidth()
                    .padding(8.dp)
                    .size(width = 120.dp, height = 45.dp)
            ) {
                Text(text = "Upload")
            }

            Button(
                onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && Environment.isExternalStorageManager()) {
                        downloadState.value = null
                        bindDownloadFile(networkManager, token,context) { state ->
                            downloadState.value = state
                            Log.d("testDownload", "$state")
                        }
                    } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R &&
                        ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        downloadState.value = null
                        bindDownloadFile(networkManager, token, context) { state ->
                            downloadState.value = state
                            Log.d("testDownload", "$state")
                        }
                    } else {
                        requestFileAccessPermission(context)
                    }
                },
                modifier = Modifier
//                    .fillMaxWidth()
                    .padding(8.dp)
                    .size(width = 120.dp, height = 45.dp)
            ) {
                Text(text = "Download")
            }
            Button(
                onClick = {
                    logout(networkManager,token,context)
                },
                modifier = Modifier
//                    .fillMaxWidth()
                    .padding(8.dp)
                    .size(width = 120.dp, height = 45.dp)
            ) {
                Text(text = "Logout")
            }

        }
    }

    // Monitor upload and download status changes, and display the message
    LaunchedEffect(uploadState.value) {
        uploadState.value?.let { state ->
            when (state) {
                is UploadState.Success -> {
                    Toast.makeText(context, "Upload successfully", Toast.LENGTH_SHORT).show()
                }

                is UploadState.Failure -> {
                    Toast.makeText(context, "Upload failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    LaunchedEffect(downloadState.value) {
        downloadState.value?.let { state ->
            when (state) {
                is DownloadState.Success -> {
                    Toast.makeText(context, "Download successfully", Toast.LENGTH_SHORT).show()
                }

                is DownloadState.Failure -> {
                    Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}




// Define upload status
sealed class UploadState {
    object Success : UploadState()
    data class Failure(val error: Throwable) : UploadState()
}

// Define download status
sealed class DownloadState {
    object Success : DownloadState()
    data class Failure(val error: Throwable) : DownloadState()
}

private fun bindUploadFile(networkManager: NetworkManager, token: String, context: Context, userId:Long, onUploadStateChange: (UploadState) -> Unit) {
//    val dbFilePath = "android.resource://${context.packageName}/raw/your_db_file_name"

    val sdCardPath = Environment.getExternalStorageDirectory().absolutePath
    val dbFilePath = "$sdCardPath/EMADATA.db"
    networkManager.uploadDbFile(dbFilePath, token, userId,
        successCallback = {
            onUploadStateChange(UploadState.Success)
        },
        errorCallback = { error,errorCode->
            if (errorCode == 30007) {
                Toast.makeText(context, error.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                navigateToLogin(context)
            }
            onUploadStateChange(UploadState.Failure(error))
        }
    )
}

private fun bindDownloadFile(networkManager: NetworkManager, token: String,context: Context, onDownloadStateChange: (DownloadState) -> Unit) {
//    val targetFilePath = applicationContext.filesDir.absolutePath + File.separator + "downloaded_database.db"
    val sdCardPath = Environment.getExternalStorageDirectory().absolutePath
    // List of files to delete
    val filesToDelete = listOf("EMADATA.db", "EMADATA.db-shm", "EMADATA.db-wal")

    // Delete files if they exist
    for (filename in filesToDelete) {
        val file = File("$sdCardPath/$filename")
        if (file.exists()) {
            file.delete()
        }
    }

    val targetFilePath = "$sdCardPath/EMADATA.db"
    networkManager.downloadDbFile(targetFilePath, token,
        successCallback = { success ->
            if (success) {
                onDownloadStateChange(DownloadState.Success)
            } else {
                onDownloadStateChange(DownloadState.Failure(Throwable("Download failed")))
            }
        },
        errorCallback = { error, errorCode ->
            if (errorCode == 30007) {
                Toast.makeText(context, error.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                navigateToLogin(context)
            }
            onDownloadStateChange(DownloadState.Failure(error))
        }
    )
}

private fun navigateToLogin(context: Context) {
    val intent = Intent(context, LoginActivity::class.java)
    context.startActivity(intent)

    // Optionally, if the context is an Activity and you want to finish it:
    if (context is Activity) {
        context.finish()
    }
}

private fun logout(networkManager: NetworkManager, token: String,context:Context){
    networkManager.logOut(token,
        successCallback = { success ->
            // jump to MainActivity
            Toast.makeText(context, "Log out Successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        },
        errorCallback = { error ->
            // show error message
            Toast.makeText(context, error.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        } )
    // End the current Activity and start LoginActivity
}
