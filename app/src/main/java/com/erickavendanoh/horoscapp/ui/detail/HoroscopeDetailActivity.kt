package com.erickavendanoh.horoscapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.navArgs
import com.erickavendanoh.horoscapp.R
import com.erickavendanoh.horoscapp.databinding.ActivityHoroscopeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

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
        args.type //Acá recuperamos el parámetro que se le paso a este Activity desde el HoroscopeFragment, que es la variable "type" que tiene el tipo de objeto de HoroscopeInfo del que es el item seleccionado del Recycler View de allá
    }
}