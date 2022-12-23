package global.msnthrp.pusher.domain.interactor.messaging

import global.msnthrp.pusher.domain.entity.RemoteMessage
import global.msnthrp.pusher.domain.entity.User

interface SenderDataSource {

    suspend fun sendMessage(user: User, message: RemoteMessage)
}