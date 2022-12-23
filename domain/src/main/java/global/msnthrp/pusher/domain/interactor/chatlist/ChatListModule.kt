package global.msnthrp.pusher.domain.interactor.chatlist

import org.koin.dsl.module

val chatListModule = module {
    single {
        ChatListInteractor(
            chatDataSource = get()
        )
    }
}