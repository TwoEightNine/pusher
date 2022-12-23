package global.msnthrp.pusher.data.firebase

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class PusherMessagingService : FirebaseMessagingService() {

    private val remoteMessagesProvider by inject<RemoteMessagesProvider>()

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        CoroutineScope(Dispatchers.Main).launch {
            remoteMessagesProvider.sendRemoteMessage(message.data)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}