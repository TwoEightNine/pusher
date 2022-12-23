package global.msnthrp.pusher.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import global.msnthrp.pusher.databinding.FragmentProfileBinding
import global.msnthrp.pusher.ui.MvvmFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : MvvmFragment<ProfileState, ProfileEvent, ProfileViewModel, FragmentProfileBinding>() {

    override val viewModel: ProfileViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadProfile()
        viewModel.loadProfileCode()
    }

    override fun renderState(state: ProfileState) {
        binding.tv.text = binding.tv.text.toString() + "\n${state.user}"
    }

    override fun handleEvent(command: ProfileEvent) {
        when (command) {
            is CodeBitmapReady -> {
                binding.iv.setImageBitmap(command.bitmap)
            }
        }
    }
}