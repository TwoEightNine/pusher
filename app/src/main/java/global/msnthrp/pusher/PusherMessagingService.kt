package global.msnthrp.pusher

import com.google.firebase.messaging.FirebaseMessagingService

class PusherMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}