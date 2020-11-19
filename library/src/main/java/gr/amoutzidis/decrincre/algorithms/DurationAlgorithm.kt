package gr.amoutzidis.decrincre.algorithms

interface DurationAlgorithm{
    fun calculate(duration: Long, previousValue: Number, newValue: Number): Long

    companion object {
        val default: DurationAlgorithm =
            DefaultDurationAlgorithm()
    }
}