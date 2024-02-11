// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    //Acá se están añadiendo estas dependencias a nivel de aplicación
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id ("com.google.dagger.hilt.android") version "2.48" apply false
    id ("androidx.navigation.safeargs.kotlin") version "2.7.1" apply false //safeargs. Esta importación colocando el ".kotlin" al final es para cuando el proyecto no tiene nada de Java, puro Kotlin, y es más ligera que la que no lo lleva.
}