package global.msnthrp.pusher.chatlist

import global.msnthrp.pusher.domain.entity.User

data class ChatListState(
    val chats: List<User> = emptyList()
)