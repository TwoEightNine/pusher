package global.msnthrp.pusher.domain.interactor.code

import android.net.Uri
import global.msnthrp.pusher.domain.entity.User

class CodeInteractor {

    fun parseUser(code: String): User? {
        val uri = Uri.parse(code)
        val parameters = uri.queryParameterNames
            .associateWith { key -> uri.getQueryParameter(key) }
        val user = parameters[KEY_NAME]?.let { name ->
            parameters[KEY_TOKEN]?.let { token ->
                User(name, token)
            }
        }
        return user
    }

    fun createCode(user: User): String {
        return Uri.Builder()
            .appendQueryParameter(KEY_NAME, user.name)
            .appendQueryParameter(KEY_TOKEN, user.token)
            .appendQueryParameter(KEY_VERSION, CURRENT_VERSION)
            .build()
            .toString()
    }

    companion object {
        private const val KEY_NAME = "n"
        private const val KEY_TOKEN = "t"
        private const val KEY_VERSION = "v"

        private const val CURRENT_VERSION = "1"
    }
}