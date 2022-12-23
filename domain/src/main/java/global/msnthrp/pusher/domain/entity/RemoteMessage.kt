package global.msnthrp.pusher.domain.entity

sealed class RemoteMessage {
    val type: String = this.javaClass.simpleName
    data class HelloMessage(val user: User) : RemoteMessage()
}