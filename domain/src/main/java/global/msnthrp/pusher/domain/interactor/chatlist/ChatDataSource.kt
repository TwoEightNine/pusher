package global.msnthrp.pusher.domain.interactor.chatlist

import global.msnthrp.pusher.domain.entity.User

interface ChatDataSource {
    suspend fun addUser(user: User)
    suspend fun getUsersList(): List<User>
}