package com.erickavendanoh.horoscapp.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erickavendanoh.horoscapp.R
import com.erickavendanoh.horoscapp.domain.model.HoroscopeInfo

//Al principio la lista que recibe el Adapter para generar y mostrar los items del recycler view ("horoscopeList") estará vacía. Ya después se agregarán elementos a esa lista.
//Lo de "private val onItemSelected:(HoroscopeInfo) -> Unit" corresponde a lo de la función lambda para ir a la página de detalles del horoscopo seleccionado
class HoroscopeAdapter(private var horoscopeList:List<HoroscopeInfo> = emptyList(), private val onItemSelected:(HoroscopeInfo) -> Unit) : RecyclerView.Adapter<HoroscopeViewHolder>() {

    //Para poner los elementos de la lista con los que se va a mostrar el Recycler View, en este caso se mandará a llamar esta función desde el main (HoroscopeFragment) pasándole una lista definida en el ViewModel (HoroscopeViewModel) como parámetro a esta función
    fun updateList(list: List<HoroscopeInfo>){
        horoscopeList = list
        notifyDataSetChanged() //Se notifica que hubo cambios en los datos (en este caso lista) con los que va a trabajar el Adapter. Nota: Android Studio marca una advertencia al usar este método ya que menciona que no es tan eficiente cuando se cambian pocos datos porque prácticamente evalúa tooodo de nuevo y eso gasta muchos recursos. Pero en este caso como solo se cambiarán poco o hasa una vez nada más no pasa nada
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        return HoroscopeViewHolder(
            //Lo de "LayoutInflater.from()" como primer parámetro necesita el contexto (context), recordar que todas las vistas tienen ya el contexto (no hay necesidad de crear un parámetro nosotros mismos para eso, porque es una MALA práctica, ademas que no es necesario), en este caso empleamos el "parent" que es el primer parámetro del método "onCreateViewHolder()" que es un "ViewGroup" (una vista también)
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        )
    }

    override fun getItemCount() = horoscopeList.size

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        holder.render(horoscopeList[position], onItemSelected)
    }
}