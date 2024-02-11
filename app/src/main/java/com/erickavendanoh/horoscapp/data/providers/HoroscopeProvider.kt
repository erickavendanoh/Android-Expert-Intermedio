package com.erickavendanoh.horoscapp.data.providers

import com.erickavendanoh.horoscapp.domain.model.HoroscopeInfo
import com.erickavendanoh.horoscapp.domain.model.HoroscopeInfo.*
import javax.inject.Inject

//Lo de "@Inject constructor()" es para indicar que se pueda inyectar (relacionado a lo de DaggerHilt), en este caso porque este Provider se emeplará en "HoroscopeViewModel" donde también se hace uso del DaggerHilt para poder emplearlo allá
class HoroscopeProvider @Inject constructor() {
    fun getHoroscope() : List<HoroscopeInfo>  {
        return listOf(
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
        )
    }
}