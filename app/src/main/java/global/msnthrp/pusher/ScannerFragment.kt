package global.msnthrp.pusher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import global.msnthrp.pusher.databinding.FragmentScannerBinding
import global.msnthrp.pusher.ui.BaseFragment
import global.msnthrp.pusher.ui.setNavigationResult

class ScannerFragment : BaseFragment<FragmentScannerBinding>() {


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentScannerBinding = FragmentScannerBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.barcodeView.decodeSingle { result ->
            val codeData = result.text
            setNavigationResult(codeData)
            onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.barcodeView.resume()
    }

    override fun onPause() {
        binding.barcodeView.pause()
        super.onPause()
    }
}