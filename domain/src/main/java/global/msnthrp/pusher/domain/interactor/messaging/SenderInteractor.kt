package global.msnthrp.pusher.domain.interactor.messaging

import android.util.Log
import global.msnthrp.pusher.domain.entity.RemoteMessage
import global.msnthrp.pusher.domain.entity.User
import global.msnthrp.pusher.domain.interactor.chatlist.ChatDataSource
import global.msnthrp.pusher.domain.interactor.profile.ProfileDataSource

class SenderInteractor(
    private val profileDataSource: ProfileDataSource,
    private val chatDataSource: ChatDataSource,
    private val messagingDataSource: MessagingDataSource,
    private val senderDataSource: SenderDataSource
) {

    init {
        messagingDataSource.setRemoteMessagesListener(::onRemoteMessageReceived)
    }

    private val usersAwaitingHelloFrom = mutableListOf<User>()

    suspend fun sendHello(user: User) {
        usersAwaitingHelloFrom.add(user)
        profileDataSource.getCurrentUser()?.also { currentUser ->
            senderDataSource.sendMessage(user, RemoteMessage.HelloMessage(currentUser))
        }
    }

    private suspend fun onRemoteMessageReceived(remoteMessage: RemoteMessage) {
        Log.i("qwer", "received = $remoteMessage")
        when (remoteMessage) {
            is RemoteMessage.HelloMessage -> onHelloReceived(remoteMessage)
        }
    }

    private suspend fun onHelloReceived(remoteMessage: RemoteMessage.HelloMessage) {
        val incomingUser = remoteMessage.user
        val knownUsers = chatDataSource.getUsersList()
        val isIncomingUserKnown = (knownUsers + usersAwaitingHelloFrom)
            .any { it.token == incomingUser.token }
        if (isIncomingUserKnown) {
            usersAwaitingHelloFrom.remove(incomingUser)
            chatDataSource.addUser(incomingUser)
        } else {
            sendHello(remoteMessage.user)
        }
    }
}