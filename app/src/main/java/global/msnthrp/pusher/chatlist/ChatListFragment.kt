package global.msnthrp.pusher.chatlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import global.msnthrp.pusher.databinding.FragmentChatListBinding
import global.msnthrp.pusher.ui.MvvmFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatListFragment : MvvmFragment<ChatListState, Unit, ChatListViewModel, FragmentChatListBinding>() {

    override val viewModel by viewModel<ChatListViewModel>()

    private val barcodeLauncher = registerForActivityResult(ScanContract(), ::onScanResult)

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatListBinding = FragmentChatListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabScan.setOnClickListener {
            val scanOptions = ScanOptions()
                .setBeepEnabled(false)
                .setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                .setOrientationLocked(true)
            barcodeLauncher.launch(scanOptions)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadChatList()
    }

    override fun renderState(state: ChatListState) {
        binding.tv.text = state.chats.joinToString(separator = "\n")
    }

    private fun onScanResult(result: ScanIntentResult) {
        if (result.contents != null) {
            viewModel.addUser()
        }
    }
}