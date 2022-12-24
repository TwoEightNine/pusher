package global.msnthrp.pusher.domain.entity

data class User(
    val name: String,
    val token: String,
    val photoUrl: String?
)