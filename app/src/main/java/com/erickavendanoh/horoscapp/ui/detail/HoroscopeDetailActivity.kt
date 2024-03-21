package com.erickavendanoh.horoscapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.navigation.navArgs
import com.erickavendanoh.horoscapp.R
import com.erickavendanoh.horoscapp.databinding.ActivityHoroscopeDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    //Para recuperar lo que se le pasa como parámetro a esta Activity (en este caso desde HoroscopeFragment)
    private val args: HoroscopeDetailActivityArgs by navArgs() //Delegado. El nombre del tipo de dato o de la clase de la qué es es el mismo nombre del Activity seguido de "Args"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //args.type //Acá recuperamos el parámetro que se le paso a este Activity desde el HoroscopeFragment, que es la variable "type" que tiene el tipo de objeto de HoroscopeInfo del que es el item seleccionado del Recycler View de allá
        initUI()
    }

    private fun initUI() {
        initUIState()
    }


    private fun initUIState() {
        //Para "enganchar" o asociar esta Activity al StateFlow de tipo "HoroscopeDetailState" contenido en "_state" en HoroscopeDetailViewModel al que se accede a su valor mediante "state". Acá es donde se indicará que debe mostrar o hacer la pantalla según el estado en el que esté (instancia de HoroscopeDetailState correspondiente, que se conocerá mediante el StateFlow)
        lifecycleScope.launch { //Lo de la co-rutina que ejecuta un hilo solo mientras este Activity esté activo, es decir mientras se esté mostrando nada más
            //Todo esto se estará ejecutando cada que el Actvity cambie de estado
            repeatOnLifecycle(Lifecycle.State.STARTED){
                horoscopeDetailViewModel.state.collect{
                    //Se evalúa que estado es según la instancia de HoroscopeDetailState que se encuentre en el StateFlow "_state" (recuperada mediante "state") en un momento dado. Como se está evaluando una instancia de HoroscopeDetailState se deben considerar todas las instancias definidas dentro de él acá en el when
                    when(it){
                        //Normalmente dentro de los when en cada caso después del "->" va código entre "{}", pero estas no son necesarias si solo es una línea de código
                        is HoroscopeDetailState.Error -> errorState()
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Success -> successState()
                    }
                }
            }
        }
    }

    //Función en la que se harán todas las configuraciones relacionadas a lo que se mostrará en pantalla cuando esta esté en estado "cargando"
    private fun loadingState() {
        //Se muestra el progress bar
        binding.pb.isVisible = true
    }

    //Función en la que se harán todas las configuraciones relacionadas a lo que se mostrará en pantalla cuando esta esté en estado "error"
    private fun errorState(){

    }

    //Función en la que se harán todas las configuraciones relacionadas a lo que se mostrará en pantalla cuando esta esté en estado "success"
    private fun successState(){

    }

}