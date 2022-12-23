package global.msnthrp.pusher

import global.msnthrp.pusher.chatlist.ChatListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        ChatListViewModel(
            chatListInteractor = get()
        )
    }
}