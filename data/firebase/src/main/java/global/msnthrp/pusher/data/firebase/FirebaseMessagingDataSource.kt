package global.msnthrp.pusher.data.firebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import global.msnthrp.pusher.domain.entity.RemoteMessage
import global.msnthrp.pusher.domain.entity.User
import global.msnthrp.pusher.domain.interactor.messaging.MessagingDataSource
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FirebaseMessagingDataSource : MessagingDataSource, RemoteMessagesProvider {

    private var onMessageReceived: (suspend (RemoteMessage) -> Unit)? = null

    override suspend fun sendRemoteMessage(rawRemoteMessage: Map<String, String>) {
        kotlin.runCatching { rawRemoteMessage.toRemoteMessage() }.getOrNull()?.also { remoteMessage ->
            onMessageReceived?.invoke(remoteMessage)
        }
    }

    override fun setRemoteMessagesListener(onMessageReceived: suspend (RemoteMessage) -> Unit) {
        this.onMessageReceived = onMessageReceived
    }

    override suspend fun getToken(): String {
        return suspendCancellableCoroutine { continuation ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("qwer", "token = ${task.result}")
                    continuation.resume(task.result)
                } else {
                    val exception = task.exception
                        ?: RuntimeException("Firebase failed without explicit exception")
                    continuation.cancel(exception)
                }
            }
        }
    }

    private fun Map<String, String>.toRemoteMessage(): RemoteMessage? {
        val type = this["type"]
        val gson = Gson()
        return when (type) {
            "HelloMessage" -> {
                val user = gson.fromJson(this["user"], User::class.java)
                RemoteMessage.HelloMessage(user)
            }
            else -> null
        }
    }
}