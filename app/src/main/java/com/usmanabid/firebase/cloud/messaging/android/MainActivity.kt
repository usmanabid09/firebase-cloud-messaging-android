package com.usmanabid.firebase.cloud.messaging.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.lifecycleScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.usmanabid.firebase.cloud.messaging.android.ui.theme.FirebaseCloudMessagingAndroidTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch { getToken() }
        setContent { AppContentView() }
    }

    private suspend fun getToken() {
        runCatching { Firebase.messaging.token.await() }
            .onFailure { Log.d("MainViewModel", "Failed to obtain the firebase token") }
            .onSuccess { Log.d("MainViewModel", "Token: $it") }
    }

    @Composable
    fun AppContentView() {
        FirebaseCloudMessagingAndroidTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                WelcomeView(getString(R.string.welcome_message))
            }
        }
    }
}

@Composable
fun WelcomeView(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message, textAlign = TextAlign.Center)
    }
}