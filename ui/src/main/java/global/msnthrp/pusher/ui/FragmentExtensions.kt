package global.msnthrp.pusher.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController

fun <T> Fragment.getNavigationResult(key: String = "result"): MutableLiveData<T>? {
    return findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData(key)
}

fun <T> Fragment.setNavigationResult(result: T, key: String = "result") {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}