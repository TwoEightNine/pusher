package global.msnthrp.pusher.chatlist

import androidx.lifecycle.viewModelScope
import global.msnthrp.pusher.domain.entity.User
import global.msnthrp.pusher.domain.interactor.chatlist.ChatListInteractor
import global.msnthrp.pusher.domain.interactor.code.CodeInteractor
import global.msnthrp.pusher.ui.BaseViewModel
import kotlinx.coroutines.launch

class ChatListViewModel(
    private val chatListInteractor: ChatListInteractor,
    private val codeInteractor: CodeInteractor
) : BaseViewModel<ChatListState, Unit>() {

    override fun getInitialViewState() = ChatListState()

    fun loadChatList() {
        viewModelScope.launch {
            val users = chatListInteractor.getChatList()
            mutateState { copy(chats = users) }
        }
    }

    fun addUser(code: String) {
        viewModelScope.launch {
            codeInteractor.parseUser(code)?.also { user ->
                chatListInteractor.addChat(user)
                loadChatList()
            }
        }
    }
}