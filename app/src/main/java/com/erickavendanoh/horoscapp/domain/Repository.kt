package com.erickavendanoh.horoscapp.domain

//Esta clase corresponde a un "repositorio" el cuál es como un intermediario entre la capa de "data" y la capa de "domain", donde "domain" prácticamente es "no me interesa como consigas la información que necesito, simplemente dámela" y la capa de "data" es la que trabaja para conseguir esa información, pero como estas capas no pueden estar directamente relacionadas basándonos en clean architecture, se empleará esta interfaz como medio de comunicación entre estas dos
//En esta interfaz (en capa domain) se colocará lo correspondiente a solicitar la información a la capa de "data". Ya después en la capa de "data" se creará una clase (llamada "RepositoryImpl") donde se implementará esta interfaz y allí se encontrará la lógica y demás necesaria para recuperar esa información
//*Nota sobre para que se hace esto del "repositorio" con su respectiva interfaz en capa domain y su clase que la implememta en capa data en "Notes about project stuff"
interface Repository {
    suspend fun getPrediction(sign:String){

    }
}