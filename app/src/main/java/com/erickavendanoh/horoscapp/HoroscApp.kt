package com.erickavendanoh.horoscapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Para configurar DaggerHilt solo fue agregar lo respectivo en los "build.gradle.kts", crear este archivo/clase, y ya aquí solo fue agregar "@HiltAndroidApp" y su respectivo import y ya
//Eso del "@" indica que se está trabajando con un Annotation, y mediante estas se le indica al kapt (autogenerador de clases) lo que tiene que hacer con cada una de las cosas
@HiltAndroidApp
class HoroscApp: Application()