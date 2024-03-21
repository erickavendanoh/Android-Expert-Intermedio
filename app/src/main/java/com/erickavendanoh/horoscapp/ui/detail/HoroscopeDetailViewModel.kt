package com.erickavendanoh.horoscapp.ui.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor():ViewModel() {

    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading) //Variable (privada) que contendrá lo contenido en el StateFlow que tenga la información, en este caso la instancia correspondiente en un momento dado de HoroscopeDetailState. Se debe pasar un estado inicial aquí en la instancia, en este caso la instancia de "Loading".
    val state: StateFlow<HoroscopeDetailState> = _state //Constante mediante la cuál se obtendrá lo que en su momento contenga la variable privada "_state"

}