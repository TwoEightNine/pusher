package global.msnthrp.pusher.data.profile

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import global.msnthrp.pusher.domain.entity.User
import global.msnthrp.pusher.domain.interactor.profile.ProfileDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SomeProfileDataSource(private val applicationContext: Context) : ProfileDataSource {

    private val Context.profileDataStore by preferencesDataStore(name = "profile")

    private val name = stringPreferencesKey(name = "name")
    private val token = stringPreferencesKey(name = "token")
    private val photoUrl = stringPreferencesKey(name = "photoUrl")

    override suspend fun getCurrentUser(): User {
        return applicationContext.profileDataStore
            .data
            .map { it.toUser() }
            .first()
            ?: User(name = "No Name", token = "-", photoUrl = null)
    }

    override suspend fun saveCurrentUser(user: User) {
        applicationContext.profileDataStore.edit { preferences ->
            preferences.updateUser(user)
        }
    }

    private fun Preferences.toUser(): User? {
        return this[name]?.let { name ->
            this[token]?.let { token ->
                val photoUrl = this[photoUrl]
                User(name, token, photoUrl)
            }
        }
    }

    private fun MutablePreferences.updateUser(user: User) {
        this[name] = user.name
        this[token] = user.token
        val photoUrlValue = user.photoUrl
        if (photoUrlValue != null) {
            this[photoUrl] = photoUrlValue
        } else {
            this.remove(photoUrl)
        }
    }
}