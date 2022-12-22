package global.msnthrp.pusher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import global.msnthrp.pusher.databinding.FragmentProfileBinding
import global.msnthrp.pusher.domain.interactor.profile.ProfileInteractor
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {

    private val profileInteractor by inject<ProfileInteractor>()

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            val user = profileInteractor.getCurrentUser()
            binding.tv.text = binding.tv.text.toString() + "\n$user"
        }
    }
}