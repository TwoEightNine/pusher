package global.msnthrp.pusher.domain

import global.msnthrp.pusher.domain.interactor.profile.profileModule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val domainModules = module {
    loadKoinModules(profileModule)
}