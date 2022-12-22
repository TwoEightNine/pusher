package global.msnthrp.pusher.domain.interactor.profile

import global.msnthrp.pusher.domain.entity.User

interface ProfileDataSource {
    suspend fun getCurrentUser(): User
    suspend fun saveCurrentUser(user: User)
}