package com.erickavendanoh.horoscapp.data

import com.erickavendanoh.horoscapp.domain.Repository

//Clase (en capa "data") donde se implementa la interfaz Repository (en capa "domain") y donde se ejecuta la lógica necesaria para recuperar la información que se necesitará y que se definió por recuperar en la interfaz
//*Recordar que las interfaces son como contratos, por lo que todas las funciones declaradas en la interfaz se deben emplear y definir en la clase que implementa esa interfaz
//*Nota sobre para que se hace esto del "repositorio" con su respectiva interfaz en capa domain y su clase que la implememta en capa data en "Notes about project stuff"
class RepositoryImpl:Repository {
    override suspend fun getPrediction(sign: String) {
        //Llamar Retrofit
    }
}