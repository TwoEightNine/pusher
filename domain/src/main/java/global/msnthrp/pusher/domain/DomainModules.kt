package global.msnthrp.pusher.domain

import global.msnthrp.pusher.domain.interactor.chatlist.chatListModule
import global.msnthrp.pusher.domain.interactor.code.CodeInteractor
import global.msnthrp.pusher.domain.interactor.messaging.SenderInteractor
import global.msnthrp.pusher.domain.interactor.profile.profileModule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val domainModules = module {
    loadKoinModules(
        listOf(
            profileModule,
            chatListModule,
        )
    )

    single { CodeInteractor() }
    single {
        SenderInteractor(
            messagingDataSource = get(),
            senderDataSource = get(),
            profileDataSource = get(),
            chatDataSource = get()
        )
    }
}