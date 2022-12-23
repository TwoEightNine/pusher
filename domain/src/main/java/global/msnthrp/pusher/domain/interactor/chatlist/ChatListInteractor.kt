package global.msnthrp.pusher.domain.interactor.chatlist

import global.msnthrp.pusher.domain.entity.User

class ChatListInteractor(
    private val chatDataSource: ChatDataSource
) {

    suspend fun getChatList(): List<User> {
        return chatDataSource.getUsersList()
    }

    suspend fun addChat(user: User) {
        chatDataSource.addUser(user)
    }
}