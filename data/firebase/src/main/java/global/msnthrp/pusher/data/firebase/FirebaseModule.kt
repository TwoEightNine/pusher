package global.msnthrp.pusher.data.firebase

import global.msnthrp.pusher.domain.interactor.profile.MessagingDataSource
import org.koin.dsl.module

val firebaseModule = module {
    single<MessagingDataSource> { FirebaseMessagingDataSource() }
}