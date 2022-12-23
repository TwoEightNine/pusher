package global.msnthrp.pusher.chatlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import global.msnthrp.pusher.R
import global.msnthrp.pusher.databinding.FragmentChatListBinding
import global.msnthrp.pusher.ui.MvvmFragment
import global.msnthrp.pusher.ui.getNavigationResult
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatListFragment : MvvmFragment<ChatListState, Unit, ChatListViewModel, FragmentChatListBinding>() {

    override val viewModel by viewModel<ChatListViewModel>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatListBinding = FragmentChatListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabScan.setOnClickListener {
            findNavController().navigate(R.id.action_chatListFragment_to_scannerFragment)
        }
        binding.fabScan.setOnLongClickListener {
            findNavController().navigate(R.id.action_chatListFragment_to_profileFragment)
            true
        }
        getNavigationResult<String>()?.observe(viewLifecycleOwner, viewModel::addUser)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadChatList()
    }

    override fun renderState(state: ChatListState) {
        binding.tv.text = state.chats.joinToString(separator = "\n")
    }
}