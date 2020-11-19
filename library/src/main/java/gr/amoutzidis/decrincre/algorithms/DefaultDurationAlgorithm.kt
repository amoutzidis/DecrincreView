package gr.amoutzidis.decrincre.algorithms

import kotlin.math.abs

class DefaultDurationAlgorithm:
    DurationAlgorithm {

    override fun calculate(duration: Long, previousValue: Number, newValue: Number): Long {
        val previousValueToFloat = previousValue.toFloat()
        val newValueToFloat = newValue.toFloat()
        val delta = abs(previousValueToFloat - newValueToFloat)
        return if(delta < 600)
            duration
        else
            (duration * 1.5).toLong()
    }

}