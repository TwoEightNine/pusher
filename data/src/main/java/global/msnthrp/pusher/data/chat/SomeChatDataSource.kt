package global.msnthrp.pusher.data.chat

import global.msnthrp.pusher.domain.entity.User
import global.msnthrp.pusher.domain.interactor.chatlist.ChatDataSource

class SomeChatDataSource : ChatDataSource {

    private val users = mutableListOf<User>()

    override suspend fun addUser(user: User) {
        users.add(user)
    }

    override suspend fun getUsersList(): List<User> {
        return users
    }
}