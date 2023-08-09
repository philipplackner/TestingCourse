package com.plcoding.testingcourse.part12.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.plcoding.testingcourse.ui.theme.TestingCourseTheme


class ProfileActivity : ComponentActivity() {

    private val prefs by lazy {
        applicationContext.getSharedPreferences("prefs", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scrollPosition = prefs.getInt("scroll_position", 0)
        setContent {
            TestingCourseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val lazyListState = rememberLazyListState(
                        initialFirstVisibleItemIndex = scrollPosition
                    )
                    val firstVisibleItemIndex by remember {
                        derivedStateOf {
                            lazyListState.firstVisibleItemIndex
                        }
                    }

                    val lifecycleOwner = LocalLifecycleOwner.current

                    DisposableEffect(lifecycleOwner, firstVisibleItemIndex) {
                        val observer = LifecycleEventObserver { _, event ->
                            if(event == Lifecycle.Event.ON_PAUSE) {
                                prefs.edit()
                                    .putInt("scroll_position", firstVisibleItemIndex)
                                    .apply()
                            }
                        }
                        lifecycleOwner.lifecycle.addObserver(observer)

                        onDispose {
                            lifecycleOwner.lifecycle.removeObserver(observer)
                        }
                    }

                    LazyColumn(
                        state = lazyListState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(100) {
                            Text(
                                text = "Item$it",
                                 modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestingCourseTheme {
        Greeting("Android")
    }
}