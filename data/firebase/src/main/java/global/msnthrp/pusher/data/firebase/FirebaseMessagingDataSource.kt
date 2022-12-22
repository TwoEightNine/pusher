package global.msnthrp.pusher.data.firebase

import com.google.firebase.messaging.FirebaseMessaging
import global.msnthrp.pusher.domain.interactor.profile.MessagingDataSource
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FirebaseMessagingDataSource : MessagingDataSource {

    override suspend fun getToken(): String {
        return suspendCancellableCoroutine { continuation ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    continuation.resume(task.result)
                } else {
                    continuation.cancel(task.exception ?: RuntimeException("Firebase failed without explicit exception"))
                }
            }
        }
    }
}