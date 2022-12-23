package global.msnthrp.pusher.profile

import androidx.lifecycle.viewModelScope
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import global.msnthrp.pusher.domain.interactor.code.CodeInteractor
import global.msnthrp.pusher.domain.interactor.messaging.SenderInteractor
import global.msnthrp.pusher.domain.interactor.profile.ProfileInteractor
import global.msnthrp.pusher.ui.BaseViewModel
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileInteractor: ProfileInteractor,
    private val codeInteractor: CodeInteractor,
) : BaseViewModel<ProfileState, ProfileEvent>() {

    override fun getInitialViewState() = ProfileState()

    fun loadProfile() {
        viewModelScope.launch {
            val user = profileInteractor.getCurrentUser()
            mutateState { copy(user = user) }
        }
    }

    fun loadProfileCode() {
        viewModelScope.launch {
            val user = currentState.user ?: profileInteractor.getCurrentUser()
            val bitmap = try {
                val data = codeInteractor.createCode(user)
                val multiFormatWriter = MultiFormatWriter()
                val bitMatrix = multiFormatWriter.encode(
                    data, BarcodeFormat.QR_CODE,
                    512, 512
                )
                val barcodeEncoder = BarcodeEncoder()
                barcodeEncoder.createBitmap(bitMatrix)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            bitmap?.also { postEvent(CodeBitmapReady(bitmap)) }
        }
    }
}