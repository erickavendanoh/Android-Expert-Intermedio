package com.erickavendanoh.horoscapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.erickavendanoh.horoscapp.R
import com.erickavendanoh.horoscapp.databinding.ActivityMainBinding

//En esta aplicación, a diferencia de las hechas en el curso básico (proyecto "AndroidMaster"), solo habrá un Activity y varios "fragments", ya que en las otras se hacía para cada pantalla otro Activity. En este caso no, para las diferentes pantallas se usaran los "fragments"...
//...un "fragment" es una vista o pantalla, como lo de un Activity, pero "más reutilizable" porque un Activity puede tener varios "fragments" (donde cada uno a su vez tiene su parte visual y su parte lógica, los archivos correspondientes uno dentro de la carptea correspondiente en "ui" y la vista dentro de "res"->"layout"... primero se crea el package con el nombre, relacionado a lo que va la pantalla->clic derecho->"New"->"Fragment"->Ya vienen varias opciones de algunos ya "hechos", en este caso se irá seleccionando el de "Fragment (blank)" que es el que no trae tanto para nosotros ir haciendo todo).
//En este caso este Activity será como el contenedor principal de todos los "fragments" que generemos, y este solo tendra una "tool bar", un "bottom bar" y ya lo de dentro lo de los "fragments". Esta es una forma cotidiana de trabajar en Android, por el concepto de "Single Activity" (como en Angular, con el "Single Page Application"). Y forma muy similar de trabajar en Jetpack Compose también.

class MainActivity : AppCompatActivity() {

    //Lo del ViewBinding (configurado/activado en "build.gradle.kts (Module :app)")
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController //"NavController" es la clase padre con la que se gestiona todo lo relacionado a navegación a través de la librería "Navigation component"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initNavigation()
    }

    private fun initNavigation() {
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment //Al principio sin lo de "as NavHostFragment" la constante "navHost" iba a ser del tipo "Fragment?", ahora con eso será del tipo "NavHostFragment", esto es para que no se quede genérico por lo de "Fragment?"
        navController = navHost.navController //a la variable "navController" que creamos arriba y que tiene la instancia de la clase "NavController" le estamos asignando el atrobuto "navController" de la constante "navHost". Con esto hacemos que con "navController" ya podamos controlar lo que se irá mostrando en el Fragment Container View (id "fragmentContainerView")
        binding.bottomNavView.setupWithNavController(navController) //se asigna ese controlador "navController" ya previamente configurado al Bottom Navigation View (id "bottomNavView")
    }
}