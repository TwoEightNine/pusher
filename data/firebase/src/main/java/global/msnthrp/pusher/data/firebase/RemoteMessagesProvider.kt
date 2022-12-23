package global.msnthrp.pusher.data.firebase

interface RemoteMessagesProvider {
    suspend fun sendRemoteMessage(rawRemoteMessage: Map<String, String>)
}