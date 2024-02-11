package com.erickavendanoh.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.erickavendanoh.horoscapp.databinding.FragmentHoroscopeBinding
import com.erickavendanoh.horoscapp.domain.model.HoroscopeInfo
import com.erickavendanoh.horoscapp.domain.model.HoroscopeInfo.*
import com.erickavendanoh.horoscapp.domain.model.HoroscopeModel
import com.erickavendanoh.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

//Se quitó mucho del código que ya traía "por defecto" este archivo cuando se creó el Fragment y se dejo solo el poco a utilizar, ya después se fue agregando el propio y así

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    //Para "enganchar" o conectar el Fragment (parte visual) al ViewModel
    // Es con un "delegado", recordar que se refiere a que es una única instancia y ya no se puede volver a instanciar. En este caso por ejemplo indica que la constante "horoscopeViewModel" se refiere al ViewModel "HoroscopeViewModel" y nada más, ya no podría ser a otro ViewModel
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    private lateinit var horoscopeAdapter: HoroscopeAdapter

    //El View Binding es un poco distinto en los Fragment que en los Activity
    private var _binding: FragmentHoroscopeBinding? =
        null //Esta primera variable, "_binding" tendrá lo correspondiente al View Binding del Fragment, ósea lo de la vista y así. Tiene un "_" porque es privada y "no se debería acceder a ella directamente"
    private val binding get() = _binding!! //Aquí se tiene una constante llamada "binding" a la cuál se le está sobreescribiendo su método "get()" con el cual se devuelve la variable "_binding". Por lo que, para acceder al View Binding del Fragment se hará mediante la constante "binding". Básicamente es como lo del "accesor" para atributos privados en otros lenguajes como C#, Java, etc.
    //(Los "!!" son para indicar que estamos seguros que no será null el valor)


    //Cuando se trabaja con métodos de configuración una vez que la vista ya fue creada no es correcto usar el método onCreateView(), que se usa para cosas del principio de la vista, sino que se debe usar el método onViewCreated() que ejecutará lo de dentro una vez que la vista fue creada
    // Para ponerla directa ir a pestaña "Code"->"Override Methods..."->teclear "onViewCreated" y dar Enter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
    }

    //Para inicializar lo del Recycler View
    private fun initList() {
        //Como se definió en el parámetro del Adpater (HoroscopeAdapter) que recibía una lista vacía por ahora no es necesario mandarsela cuando se instancia
        //horoscopeAdapter = HoroscopeAdapter(onItemSelected = {Toast.makeText(context, it.name, Toast.LENGTH_LONG).show()}) //Para checar que se asoció y se ejecuta correctamente la función lambda cada que se presione un item, en este caso no se tiene una función asociada a la lambda sino que es un código (dentro de las "{}", que es que muestre un texto nada más). "it" corresponde al "horoscopeInfo" al que se hace referencia tanto en el Adapter como en el ViewHolder, ya que se supone que cuando estamos acá es porque ya se estará creando cada item para el RecyclerView mediante el Adapter y ViewHolder
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {
            //Para que la pantalla de detalles (HoroscopeDetailActivity) del signo (item) seleccionado sepa de que tipo o qué signo se trata...
            //...como Google dice que NO ES RECOMENDABLE pasar como parametro un objeto completo (en este caso que podría ser "it" que es un objeto de "HoroscopeInfo") para cuando navegamos a la otra pantalla, se creó una enum class "HoroscopeModel" a través de la cuál identificaremos de que tipo de objeto de HoroscopeInfo se trata
            //*Al poner primero solo lo de "when(it){}" daba error, se da clic en el foco rojo y se selecciona "Add remaining branches" y genera el código de dentro (solo que solo pone "TODO()" después de la "->" no lo que ya está)
            val type = when(it){
                Aquarius -> HoroscopeModel.Aquarius
                Aries -> HoroscopeModel.Aries
                Cancer -> HoroscopeModel.Cancer
                Capricorn -> HoroscopeModel.Capricorn
                Gemini -> HoroscopeModel.Gemini
                Leo -> HoroscopeModel.Leo
                Libra -> HoroscopeModel.Libra
                Pisces -> HoroscopeModel.Pisces
                Sagittarius -> HoroscopeModel.Sagittarius
                Scorpio -> HoroscopeModel.Scorpio
                Taurus -> HoroscopeModel.Taurus
                Virgo -> HoroscopeModel.Virgo
            }
            //Para navegar a la respectiva pantalla de detalles del item seleccionado... mediante Navigation Component y safeargs...
            //...Ya que tenemos el tipo del que se trata (tipo de objeto del que es "it" que es un objeto de HoroscopeInfo habiéndolo evaluado previamente con el when y con ayuda de la enum class HoroscopeModel) y asignado a la constante "type", esta se le pasa como argumento a .actionHoroscopeFragmentToHoroscopeDetailActivity(), que corresponderá al parámetro "type" que se le definió previamente en "main_graph.xml" al HoroscopeDetailActivity (por eso es "safeargs", porque depsués de configurar esta parte nos "obliga" a pasar los argumentos necesarios)
            findNavController().navigate(
                //HoroscopeFragmentDirections es de las clases que genera automáticamente la líbrería "safeargs" para manejar la nanegación. En este caso esta clase sería como la correspondiente para manejar lo definido en "main_graph.xml" para este Fragment (HoroscopeFragment) y "actionHoroscopeFragmentToHoroscopeDetailActivity()" corresponde al ID del action definido dentro de él (en "main_graph.xml") que corresponde a esa respectiva navegación
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )
        })

        //Antes se tenía esto por fuera:
//        binding.rvHoroscope.layoutManager = LinearLayoutManager(context) //Acá entre los "()" a diferencia de cuando se manejaban los Recycler View en los Activity en lugar de "this" se pone "context" que por dentro es un "getContext()", para obtener el contexto.
//        binding.rvHoroscope.adapter = adapter
        //...pero ahora con esto del " binding.rvHoroscope.apply {} ", que es para estarle configurando los atributos a un componente, en este caso el Recycler View, nos ahorramos el estar repitiendo lo de "binding.rvHoroscope."
        binding.rvHoroscope.apply {
            //Se cambió el "LinearLayoutManager" que se tenía por "GridLayoutManager", esto es para que se puedan mostrar varios elementos (item) de un Recycler View en una misma fila, recibe como parámetros el contexto y el número de columnas (ósea de item por fila), en este caso 2.
            layoutManager =
                GridLayoutManager(
                    context,
                    2
                ) //Acá entre los "()" a diferencia de cuando se manejaban los Recycler View en los Activity en lugar de "this" se pone "context" que por dentro es un "getContext()", para obtener el contexto.
            adapter = horoscopeAdapter
        }
    }

    //Para inicializar el estado de la UI (vista)
    private fun initUIState() {
        //"lifecycleScope" corresponde a una  co-rutina, pero en este caso es una especial para Fragments por así decirlo, ya que solo se ejecuta mientras el Fragment se esté mostrando o esté activo y cuando no lo está no lo ejecuta es más desaparece el hilo que la esté ejecutando, esto para no gastar recursos en algo que no se está empleando. Ósea, se engancha al ciclo de vida del Fragment, por lo que cuando este "muere" esta también lo hace.
        lifecycleScope.launch {
            //Lo de "repeatOnLifecycle(Lifecycle.State.STARTED){}" se refiere a que una vez que comienza el ciclo de vida del Fragment, ósea que está activo o se está mostrando, se ejecute lo de dentro
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                //Accedemos a la lista de "_horoscope" (mediante la constante destinada para eso, "horoscope") del ViewModel, y con ".collect{}" nos suscribimos a él, es decir para la parte de que esté notificando de su valor en todo momento. Por lo que cada vez que se emplee en el ViewModel lo de "_horoscope.value=" se estará notificando acá del cambio de su valor así como valores agregados
                horoscopeViewModel.horoscope.collect {
                    //Log.i("erickavendanoh",it.toString()) //Para probar que si se está mandando la información, viéndolo desde "Logcat". "it" se refiere a "horoscope" que es lo que estamos "colectando"
                    //CAMBIOS EN HOROSCOPE
                    horoscopeAdapter.updateList(it) //"it" se refiere a "horoscope" que es lo que estamos "colectando"
                }
            }
        }
    }

    //Se quitó el método (que venía por defecto) "onCreate()". Cuando se "engancha" un Fragment a un Activity (en el cual ira dentro) su ciclo de vida es pasar por varios métodos...
    //...en este caso, el primer método que se ejecuta de los Fragments asociados a un Activity cuando se carga la vista de este es el de "onCreateView()", que es el que genera/carga la vista del Fragment ya dentró del Activity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(
            layoutInflater,
            container,
            false
        ) //Aquí se 'configura' el binding (lo que va dentro de "_binding")
        return binding.root //Y aquí se recupera ese valor que trae "binding" y se retorna, mediante la variable "binding" que fungirá como el 'accesor'
    }
}