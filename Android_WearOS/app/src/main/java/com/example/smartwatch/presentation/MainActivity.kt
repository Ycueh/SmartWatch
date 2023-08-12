/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.smartwatch.presentation

import android.Manifest
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.smartwatch.R
import com.example.smartwatch.presentation.network.NetworkManager
import com.example.smartwatch.presentation.theme.SmartWatchTheme
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val baseUrl = "http://10.0.2.2:1024/"
            val token = "eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwibmFtZSI6IueuoeeQhuWRmCIsInR5cGUiOjEsImlhdCI6MTY5MTg2NzAyMiwiZXhwIjoxNjkyNDcxODIyfQ.MLxDQDfUy6NVORZ5IvwU0UNKJpDOFJ4TUxXaizYmrVozcydhuIXXsTYNh3kSaMu3hD5IdRZqIl4jVrpyb0YACA"
            val networkManager = NetworkManager(baseUrl)

            WearApp("Android", networkManager, token)
        }
    }
}

@Composable
fun WearApp(greetingName: String, networkManager: NetworkManager, token: String) {
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
                    bindUploadFile(networkManager, token) { state ->
                        uploadState.value = state // 更新上传状态
                        Log.d("testUpload", "$state")
                    }
//                    requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Upload")
            }

            Button(
                onClick = {
                    bindDownloadFile(networkManager, token) { state ->
                        downloadState.value = state // 更新下载状态
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Download")
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
    val dbFilePath = "$sdCardPath/testema.db"
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
    val targetFilePath = Environment.getExternalStorageDirectory().absolutePath
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