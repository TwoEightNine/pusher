package global.msnthrp.pusher.domain.interactor.profile

import global.msnthrp.pusher.domain.entity.User
import global.msnthrp.pusher.domain.interactor.messaging.MessagingDataSource

class ProfileInteractor(
    private val messagingDataSource: MessagingDataSource,
    private val profileDataSource: ProfileDataSource
) {

    suspend fun getCurrentUser(): User {
        val user = profileDataSource.getCurrentUser()
        val actualToken = messagingDataSource.getToken()
        val actualUser = when {
            user.token != actualToken -> {
                val newUser = user.copy(token = actualToken)
                profileDataSource.saveCurrentUser(newUser)
                newUser
            }
            else -> user
        }
        return actualUser
    }
}