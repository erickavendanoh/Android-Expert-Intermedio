package com.erickavendanoh.horoscapp.data.network.response

import com.google.gson.annotations.SerializedName

//Modelo de datos al cuál se va a mapear/convertir la respuesta del JSON (desde el HoroscopeApiService)

//Recordar que lo de "@SerializedName("")" es para cuando se "ofusca" el código, que es cuando se prepara para salir a producción, donde generalemnte se cambian los nombres de todo, funciones, variables, clases y demás, para que haya menos caracteres y eso reduzca el peso así como hacerlo menos entendible para posibles ataques. Y esto del @SerializedName es para que Retrofit sepa a que atributo del JSON correspondera su respectiva variable aquí en Kotlin, aunque los nombres no sean iguales...
//...Lo que va dentro del "@SerializedName("")" es el nombre del atributo JSON. Y recordar, que solo se colocan los atributos que vamos a emplear, aunque la respuesta JSON tenga más atributos solo se colocan acá los que queramos recuperar, no hay por qué recuperar todos
//Todo esto también es una buena práctica
data class PredictionResponse(
    @SerializedName("date") val date: String,
    @SerializedName("horoscope") val horoscope: String,
    @SerializedName("sign") val sign: String
)