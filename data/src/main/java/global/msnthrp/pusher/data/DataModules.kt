package global.msnthrp.pusher.data

import global.msnthrp.pusher.data.chat.SomeChatDataSource
import global.msnthrp.pusher.data.firebase.firebaseModule
import global.msnthrp.pusher.data.profile.SomeProfileDataSource
import global.msnthrp.pusher.domain.interactor.chatlist.ChatDataSource
import global.msnthrp.pusher.domain.interactor.messaging.SenderDataSource
import global.msnthrp.pusher.domain.interactor.profile.ProfileDataSource
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val dataModules = module {
    loadKoinModules(firebaseModule)

    single {
        PusherDatabase.getDatabase(
            applicationContext = get()
        )
    }
    single<ProfileDataSource> {
        SomeProfileDataSource(
            applicationContext = get()
        )
    }
    single<ChatDataSource> {
        SomeChatDataSource(
            pusherDatabase = get()
        )
    }
    single<SenderDataSource> { SomeSenderDataSource() }
}