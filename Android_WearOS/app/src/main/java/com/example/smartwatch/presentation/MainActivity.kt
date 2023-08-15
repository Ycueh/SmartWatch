/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.smartwatch.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build

import androidx.core.content.ContextCompat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.smartwatch.R
import com.example.smartwatch.presentation.network.NetworkManager
import com.example.smartwatch.presentation.theme.SmartWatchTheme
import java.io.File


//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class LoginActivity : Activity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    var ip = "10.52.136.67"
//    var ip = "10.0.2.2"

    private val baseUrl = "http://$ip:1024/"
    private val networkManager = NetworkManager(baseUrl)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login) // 假设你的布局文件名为 activity_login.xml

        // 初始化视图引用
        usernameEditText = findViewById(R.id.AccountEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        // 设置点击监听器
        loginButton.setOnClickListener {
            // 执行登录操作
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            networkManager.login(username, password,
                successCallback = { token ->
                    // 跳转到 MainActivity
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("TOKEN", token)
                    startActivity(intent)
                    finish()  // 关闭 LoginActivity
                },
                errorCallback = { error ->
                    // 显示错误信息
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
//            var ip = "10.0.2.2"
            //ipv4 address
            var ip = "10.52.136.67"
            val baseUrl = "http://$ip:1024/"
            val networkManager = NetworkManager(baseUrl)
            val token = intent.getStringExtra("TOKEN")
            if (token != null) {
                //Upload Download function
                WearApp("Android", networkManager, token)
            }else{
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
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
fun WearApp(greetingName: String, networkManager: NetworkManager, token: String) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState() // 记录 Scaffold 状态
    val uploadState = remember { mutableStateOf<UploadState?>(null) } // 记录上传状态
    val downloadState = remember { mutableStateOf<DownloadState?>(null) } // 记录下载状态
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
                    // 对于 API 30 及更高版本，应用具有“所有文件访问权限”
                    bindUploadFile(networkManager, token) { state ->
                        uploadState.value = state // 更新上传状态
                        Log.d("testUpload", "$state")
                    }
                } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R &&
                    ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    // 对于 API 28 和 29，如果应用具有 WRITE_EXTERNAL_STORAGE 权限
                    bindUploadFile(networkManager, token) { state ->
                        uploadState.value = state // 更新上传状态
                        Log.d("testUpload", "$state")
                    }
                } else {
                    // 无论是 API 30+ 还是 API 28/29，只要应用没有所需的权限，就请求它
                    requestFileAccessPermission(context)  // 这里调用请求权限的函数
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Upload")
        }

        Button(
            onClick = {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && Environment.isExternalStorageManager()) {
                    // 对于 API 30 及更高版本，应用具有“所有文件访问权限”
                    bindDownloadFile(networkManager, token) { state ->
                        downloadState.value = state // 更新上传状态
                        Log.d("testUpload", "$state")
                    }
                } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R &&
                    ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    // 对于 API 28 和 29，如果应用具有 WRITE_EXTERNAL_STORAGE 权限
                    bindDownloadFile(networkManager, token) { state ->
                        downloadState.value = state // 更新上传状态
                        Log.d("testUpload", "$state")
                    }
                } else {
                    // 无论是 API 30+ 还是 API 28/29，只要应用没有所需的权限，就请求它
                    requestFileAccessPermission(context)  // 这里调用请求权限的函数
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Download")
        }
        Button(
            onClick = {
                logout(networkManager,token,context)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Logout")
        }

        // 监听上传和下载状态变化，并显示对应的 Snackbar
        LaunchedEffect(uploadState.value) {
            uploadState.value?.let { state ->
                when (state) {
                    is UploadState.Success -> {
                        scaffoldState.snackbarHostState.showSnackbar("Upload successful")
                    }
                    is UploadState.Failure -> {
                        scaffoldState.snackbarHostState.showSnackbar("Upload failed: ${state.error.message}")
                    }
                }
            }
        }

        LaunchedEffect(downloadState.value) {
            downloadState.value?.let { state ->
                when (state) {
                    is DownloadState.Success -> {
                        scaffoldState.snackbarHostState.showSnackbar("Download successful")
                    }
                    is DownloadState.Failure -> {
                        scaffoldState.snackbarHostState.showSnackbar("Download failed: ${state.error.message}")
                    }
                }
            }
        }
    }
}
}

// 定义上传状态
sealed class UploadState {
    object Success : UploadState()
    data class Failure(val error: Throwable) : UploadState()
}

// 定义下载状态
sealed class DownloadState {
    object Success : DownloadState()
    data class Failure(val error: Throwable) : DownloadState()
}

private fun bindUploadFile(networkManager: NetworkManager, token: String, onUploadStateChange: (UploadState) -> Unit) {
//    val dbFilePath = "android.resource://${context.packageName}/raw/your_db_file_name"

    val sdCardPath = Environment.getExternalStorageDirectory().absolutePath
    val dbFilePath = "$sdCardPath/EMADATA.db"
    networkManager.uploadDbFile(dbFilePath, token,
        successCallback = {
            onUploadStateChange(UploadState.Success)
        },
        errorCallback = { error ->
            onUploadStateChange(UploadState.Failure(error))
        }
    )
}

private fun bindDownloadFile(networkManager: NetworkManager, token: String, onDownloadStateChange: (DownloadState) -> Unit) {
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
        errorCallback = { error ->
            onDownloadStateChange(DownloadState.Failure(error))
        }
    )
}

private fun logout(networkManager: NetworkManager, token: String,context:Context){
    networkManager.logOut(token,
        successCallback = { success ->
            // 跳转到 MainActivity
            Toast.makeText(context, "Log out Successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        },
        errorCallback = { error ->
            // 显示错误信息
            Toast.makeText(context, error.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
        } )
    // 结束当前Activity并启动LoginActivity
}

@Composable
fun Greeting(greetingName: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = stringResource(R.string.hello_world, greetingName)
    )
}

//@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
//@Composable
//fun DefaultPreview() {
//    WearApp("Preview Android")
//}
//@Composable
//fun LoginScreen(
//    networkManager: NetworkManager,
//    onLoginSuccess: (token: String) -> Unit,  // 修改这里
//    onLoginError: (error: Throwable) -> Unit
//) {
//    val loginName = remember { mutableStateOf("") }
//    val password = remember { mutableStateOf("") }
//    val context = LocalContext.current
//
//    // 添加这部分代码
//    val focusRequester = remember { FocusRequester() }
//
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .fillMaxSize()  // 填充整个屏幕
//            .clickable { focusRequester.freeFocus() }  // 当在Column外部点击时，释放焦点
//    ) {
//        Spacer(modifier = Modifier.height(15.dp))  // 在这里增加额外的空间
//
//        Box(modifier = Modifier.width(150.dp)) { // 设置宽度以匹配你的 TextField
//            TextField(
//                value = loginName.value,
//                onValueChange = { loginName.value = it },
//                label = { }, // 留空的 label
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .focusRequester(focusRequester)
//            )
//            Text(
//                text = "Account",
//                modifier = Modifier.padding(start = 30.dp)  // 根据需要调整此值来右移标签
//            )
//        }
//        TextField(
//            value = password.value,
//            onValueChange = { password.value = it },
//            label = { Text("Password") },
//            visualTransformation = PasswordVisualTransformation(),
//            modifier = Modifier.focusRequester(focusRequester)  // 为另一个TextField添加同一个focusRequester
//        )
//        Spacer(modifier = Modifier.height(10.dp))
//        Button(onClick = {
//            // 在点击登录按钮时释放焦点
//            focusRequester.freeFocus()
//
//            networkManager.login(loginName.value, password.value,
//                successCallback = { token ->
//                    onLoginSuccess(token)  // 在这里处理token
//                },
//                errorCallback = { error ->
//                    Toast.makeText(context, error.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
//                    onLoginError(error)
//                }
//            )
//        }) {
//            Text("Login")
//        }
//    }
//}