package com.erickavendanoh.horoscapp.ui.detail

//Se considera que una pantalla puede tener varios "estados", por ahora consideraremos que puede tener tres: success, error y cuando está cargando
//Esta clase se encargará de gestionar estos estados definiendo la información necesaria para que cuando sea el caso la vista ya sepa qué hacer o qué mostrar dependiendo el estado en el que se encuentre
//Esto se logrará con una comunicación entre esta clase y el ViewModel (HoroscopeDetailViewModel), que es el que controla lo del Activity (HoroscopeDetailActivity) (parte visual) mediante StateFlow, el cuál será de tipo "HoroscopeDetailState", y ya evaluando en el ViewModel a que instancia de este se refiera es lo que el Activity mostrará
sealed class HoroscopeDetailState {
    //Cuando se está creando una instancia que no requiere parámetros, en este caso "Loading" que se refiere a un estado sencillo, se emplea "data object"
    data object Loading:HoroscopeDetailState()
    //Cuando se está creando una instancia que requiere parámetros se usa "data class"
    data class Error(val error: String):HoroscopeDetailState()
    data class Success(val data:String):HoroscopeDetailState()
}