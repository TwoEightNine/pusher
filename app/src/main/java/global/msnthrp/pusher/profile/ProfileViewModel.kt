package global.msnthrp.pusher.profile

import androidx.lifecycle.viewModelScope
import global.msnthrp.pusher.domain.interactor.profile.ProfileInteractor
import global.msnthrp.pusher.ui.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class ProfileViewModel : BaseViewModel<ProfileState, Unit>() {

    private val profileInteractor: ProfileInteractor by inject(ProfileInteractor::class.java)

    override fun getInitialViewState() = ProfileState()

    fun loadProfile() {
        viewModelScope.launch {
            val user = profileInteractor.getCurrentUser()
            mutateState { copy(user = user) }
        }
    }
}