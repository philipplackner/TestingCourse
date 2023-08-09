package com.plcoding.testingcourse

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat
import com.plcoding.testingcourse.ui.theme.TestingCourseTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestingCourseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var showPermissionError by rememberSaveable {
                        mutableStateOf(false)
                    }
                    // PERMISSION REQUEST
                    val permissionLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestPermission(),
                        onResult = { wasGranted ->
                            if(!wasGranted && !shouldShowRequestPermissionRationale(
                                    android.Manifest.permission.RECORD_AUDIO
                            )) {
                                showPermissionError = true
                            }
                        }
                    )

                    Button(onClick = {
                        val hasPermission = ActivityCompat.checkSelfPermission(
                            applicationContext,
                            android.Manifest.permission.RECORD_AUDIO
                        ) == PackageManager.PERMISSION_GRANTED

                        if(hasPermission) {
                            // Start recording...
                        } else {
                            permissionLauncher.launch(android.Manifest.permission.RECORD_AUDIO)
                        }
                    }) {
                        Text(text = "Record")
                    }
                    if(showPermissionError) {
                        Dialog(onDismissRequest = {
                            showPermissionError = false
                        }) {
                            Text(
                                text = "Can't record without permission",
                                modifier = Modifier
                                    .background(Color.White, RoundedCornerShape(100))
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}