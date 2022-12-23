package global.msnthrp.pusher

import global.msnthrp.pusher.chatlist.ChatListViewModel
import global.msnthrp.pusher.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        ChatListViewModel(
            chatListInteractor = get(),
            codeInteractor = get(),
            senderInteractor = get()
        )
    }
    viewModel {
        ProfileViewModel(
            profileInteractor = get(),
            codeInteractor = get(),
        )
    }
}