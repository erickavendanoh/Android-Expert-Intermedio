package com.erickavendanoh.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.erickavendanoh.horoscapp.data.providers.HoroscopeProvider
import com.erickavendanoh.horoscapp.domain.model.HoroscopeInfo
import com.erickavendanoh.horoscapp.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

//Extiende (hereda) de clase ViewModel (lo de ": ViewModel()")
//Lo de "horoscopeProvider: HoroscopeProvider" dentro del constructor es para poder emplear el Provider que proveera de la información necesaria a este ViewModel. Se pone acá y no como instancia dentro del cuerpo de la instancia porque puede haber parámetros o dependencias que requiera un Provider que no conozcamos, entonces poniéndolo acá DaggerHilt ya se encarga de eso...
//...*no es necesario antenponer el "val" en una variable en un constructor cuando esta solo se empleará dentro del "init{}" Ya si se requiere emplear en otras funciones si será necesario poner el "val"
@HiltViewModel
class HoroscopeViewModel @Inject constructor(horoscopeProvider: HoroscopeProvider) : ViewModel() {

    //Parte del StateFlow (para comunicación entre ViewModel y Fragment)
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList()) //Acá está la lista modificable, a la que se le podrán ir agregando, eliminando, o modificando valores
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope //Esta constante "horoscope" servirá como el accesor a lo que se tenga en la lista "_horoscope", porque se está igualando a ella y tendrá lo que esta contenga en un momento dado. Esto para evitar que se pueda modificar la lista original (en "_horoscope") y solo sea leerla, por ello la constante "horoscope" no es mutable. Esto también es una buena práctica

    //El método "init{}" en los ViewModel es el equivalente al método onCreate() de los Activity, se llama justo cuando se crea el ViewModel
    init {
        //Inicializamos la lista contenida en "_horoscope" mediante el método getHoroscope() definido en el Provider que retorna la lista allá definida
        _horoscope.value = horoscopeProvider.getHoroscope()
    }

}