package global.msnthrp.pusher.data.firebase

import global.msnthrp.pusher.domain.interactor.messaging.MessagingDataSource
import org.koin.dsl.binds
import org.koin.dsl.module

val firebaseModule = module {
    single {
        FirebaseMessagingDataSource()
    }.binds(arrayOf(MessagingDataSource::class, RemoteMessagesProvider::class))
}