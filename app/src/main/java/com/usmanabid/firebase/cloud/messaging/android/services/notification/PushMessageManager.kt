package com.usmanabid.firebase.cloud.messaging.android.services.notification

import com.google.firebase.messaging.RemoteMessage
import javax.inject.Inject

class PushMessageManager @Inject constructor() {
    suspend fun onMessageReceived(message: RemoteMessage) {
    }
}