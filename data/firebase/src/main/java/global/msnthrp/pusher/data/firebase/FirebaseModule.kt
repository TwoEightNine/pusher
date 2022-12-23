package global.msnthrp.pusher.data.firebase

import global.msnthrp.pusher.domain.interactor.messaging.MessagingDataSource
import org.koin.dsl.module

val firebaseModule = module {
    single<MessagingDataSource> { FirebaseMessagingDataSource() }
}