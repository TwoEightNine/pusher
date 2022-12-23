package global.msnthrp.pusher.domain.interactor.messaging

import global.msnthrp.pusher.domain.entity.RemoteMessage

interface MessagingDataSource {
    fun setRemoteMessagesListener(onMessageReceived: suspend (RemoteMessage) -> Unit)
    suspend fun getToken(): String
}