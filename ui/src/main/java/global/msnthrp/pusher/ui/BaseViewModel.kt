package global.msnthrp.pusher.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Event> : ViewModel() {

    private val stateFlow = MutableStateFlow(getInitialViewState())
    val state: Flow<State>
        get() = stateFlow


    private val eventFlow = MutableSharedFlow<Event>()
    val event: Flow<Event>
        get() = eventFlow

    protected val currentState: State
        get() = stateFlow.value


    protected fun mutateState(mutation: State.() -> State) {
        stateFlow.value = stateFlow.value.mutation()
    }

    protected fun postEvent(event: Event) {
        viewModelScope.launch { eventFlow.emit(event) }
    }

    protected abstract fun getInitialViewState(): State
}