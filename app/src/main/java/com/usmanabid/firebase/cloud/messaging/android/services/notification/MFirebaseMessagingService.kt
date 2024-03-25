package com.usmanabid.firebase.cloud.messaging.android.services.notification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var pushMessageManager: PushMessageManager

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        CoroutineScope(Dispatchers.IO).launch {
            runCatching { pushMessageManager.onMessageReceived(message) }
                .onSuccess { println("MY_LOG: Push notification Message Received") }
                .onFailure { println("MY_LOG: Push notification handling failed: ${it.message}") }
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        println("MLog_onNewToken: $token")
    }
}