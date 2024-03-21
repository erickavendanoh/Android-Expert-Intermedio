package com.erickavendanoh.horoscapp.data.network

import com.erickavendanoh.horoscapp.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

//Este archivo será el "enlace" entre la API y la aplicación, desde acá se harán las respectivas peticiones a la API y se 'mapearán' las respuestas en JSON que esta entregue al tipo de clase (igual definido en otros archivos) correspondiente (YA VISTO TODO ESTO EN CURSO BÁSICO)

interface HoroscopeApiService {

    //Método HTTP que corresponde a URL https://newastro.vercel.app/*signo correspondiente*/ que transforma la respuesta JSON al modelo de datos (clase) PredictionResponse
    @GET("/{sign}")
    suspend fun getHoroscope(@Path("sign") sign:String):PredictionResponse
}