package global.msnthrp.pusher.data.profile

import global.msnthrp.pusher.domain.entity.User
import global.msnthrp.pusher.domain.interactor.profile.ProfileDataSource

class SomeProfileDataSource : ProfileDataSource {

    private var user = User(
        name = "name",
        token = "huy"
    )

    override suspend fun getCurrentUser(): User {
        return user
    }

    override suspend fun saveCurrentUser(user: User) {
        this.user = user
    }
}