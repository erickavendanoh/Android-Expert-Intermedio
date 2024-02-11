package com.erickavendanoh.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.erickavendanoh.horoscapp.databinding.ItemHoroscopeBinding
import com.erickavendanoh.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)

    //Lo de "private val onItemSelected:(HoroscopeInfo) -> Unit" corresponde a lo de la función lambda para ir a la página de detalles del horoscopo seleccionado
    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        val context =
            binding.tvTitle.context //Para recuperar el contexto (necesario para la función .getString() para el Text View más abajo), porque recordar que se puede obtener el contexto de la vista desde cualquier componente
        //Acá se están asignando a los componentes de la vista de cada item del Recycler View (item_horoscope.xml) los valores con base al modelo, notar que las funciones que se emplean para mostrar las cosas en las vistas reciben valores int, que son el tipo de datos que tiene en su constructor el modelo (HoroscopeInfo)
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvTitle.text = context.getString(horoscopeInfo.name)

        //Para asociar la función lambda a cada item creado
        binding.parent.setOnClickListener {
            startRotationAnimation(binding.ivHoroscope, newLambda = {onItemSelected(horoscopeInfo)}) //Para ejecutar la animación. La función lambda definida dentro de la función para la animación mandará a llamar a la función lambda de acá (onItemSelected)
            //onItemSelected(horoscopeInfo)
        }
    }

    //Para la animación (sencilla por ahora) cuando se da clic sobre un item (que giré el icono 360°)
    fun startRotationAnimation(view: View, newLambda:()-> Unit){
        view.animate().apply {
            //El atributo "interpolator" es para manejar la velocidad de la animación en diversos momentos, por ejemplo puede ir a cierta velocidad al inicio y a punto de terminar a otra. En este caso con "LinearInterpolator()" indicamos que sea la misma desde que empieza hasta que acaba
            //"rotationBy(360f)" es para indicar cuantos grados sobre el mismo eje girará, en este caso 360°
            //Como se tenía antes, la asociación para la ejecución de la función lambda (onItemSelected) para cada item, donde se tenía dentro la ejecución de la función lambda dentro de binding.parent.setOnClickListener {...}, antes de la animación, ya no podría ir ahí porque se "traslaparían". Entonces para evitar esto se creará una nueva función lambda para esta parte de la animación...
            //...lo que va dentro de "withEndAction {  }" se ejecuta una vez que concluya la animación, es decir el tiempo de duración que se le asignó, y en este caso se ejecutará la función lambda definida acá que a su vez manda a llamar a la función lambda "onItemSelected" (que recordar que hará lo definido en el Main (en este caso el HoroscopeFragment) o mandará a llamar a la función definida allá a la que se le asocie), esto cuando se llama a esta función de la animación dentro de binding.parent.setOnClickListener {...}
            duration=500
            interpolator = LinearInterpolator()
            rotationBy(360f)
            withEndAction { newLambda() }
            start()
        }
    }
}