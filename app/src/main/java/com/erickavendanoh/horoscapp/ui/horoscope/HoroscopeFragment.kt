package com.erickavendanoh.horoscapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.erickavendanoh.horoscapp.databinding.FragmentHoroscopeBinding

//Se quitó mucho del código que ya traía "por defecto" este archivo cuando se creó el Fragment y se dejo solo el poco a utilizar, ya después se fue agregando el propio y así

class HoroscopeFragment : Fragment() {

    //El View Binding es un poco distinto en los Fragment que en los Activity
    private var _binding: FragmentHoroscopeBinding? = null //Esta primera variable, "_binding" tendrá lo correspondiente al View Binding del Fragment, ósea lo de la vista y así. Tiene un "_" porque es privada y "no se debería acceder a ella directamente"
    private val binding get() = _binding!! //Aquí se tiene una constante llamada "binding" a la cuál se le está sobreescribiendo su método "get()" con el cual se devuelve la variable "_binding". Por lo que, para acceder al View Binding del Fragment se hará mediante la constante "binding". Básicamente es como lo del "accesor" para atributos privados en otros lenguajes como C#, Java, etc.
    //(Los "!!" son para indicar que estamos seguros que no será null el valor)

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