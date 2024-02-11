package com.erickavendanoh.horoscapp.domain.model

//Clase enumerada. Aquí se tendrán todos los signos zodiacale (objetos instanciados de la misma clase en "HoroscopeInfo"), esto para la parte de navegación a pantalla de detalles del item del signo seleccionado en "HoroscopeFragment", ya que los enumerados es una forma sencilla de definir el tipo que es un objeto que se está empleando en un momento dado
enum class HoroscopeModel {
    Aries,
    Taurus,
    Gemini,
    Cancer,
    Leo,
    Virgo,
    Libra,
    Scorpio,
    Sagittarius,
    Capricorn,
    Aquarius,
    Pisces
}