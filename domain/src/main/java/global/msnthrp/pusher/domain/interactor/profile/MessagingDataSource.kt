package global.msnthrp.pusher.domain.interactor.profile

interface MessagingDataSource {
    suspend fun getToken(): String
}