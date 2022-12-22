package global.msnthrp.pusher.domain.interactor.profile

import org.koin.dsl.module

val profileModule = module {
    single {
        ProfileInteractor(
            messagingDataSource = get(),
            profileDataSource = get()
        )
    }
}