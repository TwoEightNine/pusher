package global.msnthrp.pusher.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.coroutineScope
import androidx.viewbinding.ViewBinding

abstract class MvvmFragment<VMS, VME, VM : BaseViewModel<VMS, VME>, VB : ViewBinding> :
    BaseFragment<VB>() {

    protected abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.coroutineScope.apply {
            launchWhenCreated { viewModel.state.collect(::renderState) }
            launchWhenCreated { viewModel.event.collect(::handleEvent) }
        }
    }

    abstract fun renderState(state: VMS)

    open fun handleEvent(command: VME) = Unit
}