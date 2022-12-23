package global.msnthrp.pusher

import android.app.Application
import global.msnthrp.pusher.data.dataModules
import global.msnthrp.pusher.domain.domainModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class PusherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(listOf(
                domainModules,
                dataModules,
                appModule
            ))
        }
    }
}