package global.msnthrp.pusher.domain.interactor.messaging

interface MessagingDataSource {
    suspend fun getToken(): String
}