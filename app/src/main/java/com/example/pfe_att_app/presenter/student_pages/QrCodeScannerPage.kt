package com.example.pfe_att_app.presenter.student_pages

import android.Manifest
import android.content.pm.PackageManager
import android.util.Size
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pfe_att_app.presenter.pages.schedule.ScheduleViewModel
import com.example.pfe_att_app.utils.QrCodeAnalyzer
@Composable
fun QrCodeScannerPage(seance_id:String?,navController: NavController,scheduleViewModel: ScheduleViewModel = hiltViewModel()) {
    var code by remember { mutableStateOf("") }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        }
    )
    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        if (hasCamPermission) {
            AndroidView(
                factory = { context ->
                    val previewView = PreviewView(context)
                    val cameraProvider = cameraProviderFuture.get()
                    val preview = Preview.Builder().build()
                    val imageAnalysis = ImageAnalysis.Builder()
                        .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                    val qrCodeAnalyzer = QrCodeAnalyzer { result ->
                        code = result

                    }
                    preview.setSurfaceProvider(previewView.surfaceProvider)
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        preview,
                        imageAnalysis
                    )
                    imageAnalysis.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        qrCodeAnalyzer
                    )
                    previewView
                },
                modifier = Modifier.weight(1f)
            )
            Column {
                Text(
                    text = code,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                )
                Button(onClick = {
                    if(code.isNotEmpty()){

                        navController.popBackStack()

                    }
                }) {
                    Text(text = "Validate")
                }
            }
        }
    }
}
