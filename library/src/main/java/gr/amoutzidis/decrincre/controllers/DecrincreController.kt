package gr.amoutzidis.decrincre.controllers

import android.view.animation.Interpolator
import gr.amoutzidis.decrincre.algorithms.DurationAlgorithm
import java.text.DecimalFormat

interface DecrincreController{

    fun setNumber(number: Number?, withAnimation: Boolean = true)

    fun setInterpolator(interpolator: Interpolator)
    fun getInterpolator(): Interpolator

    fun setDecimalFormatter(decimalFormatter: DecimalFormat)
    fun getDecimalFormatter(): DecimalFormat?

    fun setDurationAlgorithm(algorithm: DurationAlgorithm)
    fun getDurationAlgorithm(): DurationAlgorithm

    fun reset()
}