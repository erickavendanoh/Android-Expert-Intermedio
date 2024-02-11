package com.erickavendanoh.horoscapp.domain.model

import com.erickavendanoh.horoscapp.R

//Como en el caso de está aplicación el modelo de datos va a ser limitado y casi no cambia, e incluso es un número fijo (en este caso porque se refiere a los signos zodiacales de los cuales solo hay 12)...
//...no conviene tanto hacer una data class, sino que mejor es una sealed class, en la cuál como recordaremos ya tenemos todo "fijo" por decirlo así, en este caso los atributos que se deben tener e incluso las instancias de los que vamos a emplear. Esto también ayudará a facilitar el manejo de la lógica posteriormente.
//Se le están pasando variables "Int" como parámetro en el construtor ya que estas corresponden a las referecnias de los valores de los otros lugares donde se van a recolectar. Ósea con base a estas se va a recuperar el valor string que corresponde al "name" por ejemplo, lo mismo con "img" y así. Y ya después en el Fragment, Activity, etc. donde se vaya a emplear ahí si ya podremops usar funciones como ".getString()", ".getDrawable()", y así para ya mostrarlos. Ya que debemos recordar que acá en "domain" va la lógica del negocio, la cual debe ser lo más "general" posible, sin tener tantas cosas de un lenguaje o tecnología en especifíco, sino que debe ser en general lo que se debe hacer o recuperar independientemente de la tecnología o lenguaje a emplear.
// cuando se iban tecleando los valores dentro del constructor de cada instancia se podia ver que el tipo de dato de la referencia que tenemos para obtener los valores que traemos del directorio "drawable" y del archivo "strings.xml" respectivamente mediante "R", son del tipo Int, es por eso que en el constructor de la clase se pusieron esas variables de tipo Int
//La ventaja de tener este modelo como una sealed class y de tener las instancias ya hechas dentro es que ya podemos usar esas instancias donde sea solo colocando su nombre ("Aries, "Taurus", etc.) asegurándonos de que siempre tengan esa misma estructura definida acá con esos valores, así como que cuando queramos por ejemplo cambiar la imagen o nombre de un signo ya solo haciendo el cambio acá se reflejará en todos los lados donde se estén usando las instancias. Esto también es una buena prática porque le da más mantenibilidad al código...
//...Además que cuando agreguemos una instancia más acá y cuando se itera este modelo, por ejemplo evaluando que tipo de objeto es una instancia de esta HoroscopeInfo en un when (como en el caso en "HoroscopeFragment" en la parte de navegar a la pantalla de datelles del signo seleccionado) también se debeN considerar todos los casos de las instancias (objetos) definidos aquí, por lo que así también nos aseguramos de seguir la estrcutura definida siempre.

//*Antes las instancias solo se tenian con "object", pero con las nuevas versiones de Kotlin ahora se pueden convertir a data object (clicando el foco amarillo y seleccionando "convert to 'data object'"), y aunque básicamente no cambia nada, esto es para cuando por ejemplo en casos de querer visualizar la información de cada instancia en un Log o empleando un .toString() salga todo el objeto con la info y no solo la referencia de memoria (los números con letras "raras")
sealed class HoroscopeInfo (val img:Int, val name:Int){
    data object Aries:HoroscopeInfo(R.drawable.aries, R.string.aries)
    data object Taurus: HoroscopeInfo(R.drawable.tauro, R.string.taurus)
    data object Gemini: HoroscopeInfo(R.drawable.geminis, R.string.gemini)
    data object Cancer: HoroscopeInfo(R.drawable.cancer, R.string.cancer)
    data object Leo: HoroscopeInfo(R.drawable.leo, R.string.leo)
    data object Virgo: HoroscopeInfo(R.drawable.virgo, R.string.virgo)
    data object Libra: HoroscopeInfo(R.drawable.libra, R.string.libra)
    data object Scorpio: HoroscopeInfo(R.drawable.escorpio, R.string.scorpio)
    data object Sagittarius: HoroscopeInfo(R.drawable.sagitario, R.string.sagittarius)
    data object Capricorn: HoroscopeInfo(R.drawable.capricornio, R.string.capricorn)
    data object Aquarius: HoroscopeInfo(R.drawable.aquario, R.string.aquarius)
    data object Pisces: HoroscopeInfo(R.drawable.piscis, R.string.pisces)
}

