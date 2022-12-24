package global.msnthrp.pusher.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import global.msnthrp.pusher.databinding.FragmentProfileBinding
import global.msnthrp.pusher.ui.MvvmFragment
import global.msnthrp.pusher.ui.applyBottomInsetPadding
import global.msnthrp.pusher.ui.applyTopInsetMargin
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment :
    MvvmFragment<ProfileState, ProfileEvent, ProfileViewModel, FragmentProfileBinding>() {

    override val viewModel: ProfileViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.applyTopInsetMargin()
        binding.nestedScrollView.applyBottomInsetPadding()
        viewModel.loadProfile()
        viewModel.loadProfileCode()
    }

    override fun renderState(state: ProfileState) {
        binding.tv.text = binding.tv.text.toString() + "\n${state.user}"
//        state.user?.photoUrl?.also { photoUrl ->
//        }
        Glide.with(binding.ivChatAvatar)
            .load("https://static.wikia.nocookie.net/villains/images/7/7b/Tylerbetterpicture.jpg/revision/latest?cb=20170410170911")
            .into(binding.ivChatAvatar)
    }

    override fun handleEvent(command: ProfileEvent) {
        when (command) {
            is CodeBitmapReady -> {
                binding.iv.setImageBitmap(command.bitmap)
            }
        }
    }
}