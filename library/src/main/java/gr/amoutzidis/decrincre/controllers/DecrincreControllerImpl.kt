package gr.amoutzidis.decrincre.controllers

import android.animation.ValueAnimator
import android.content.res.TypedArray
import android.util.TypedValue
import android.view.animation.*
import gr.amoutzidis.decrincre.R
import gr.amoutzidis.decrincre.algorithms.DurationAlgorithm
import gr.amoutzidis.decrincre.animator.setValuesOfNumbers
import gr.amoutzidis.decrincre.lazy.readWrite
import java.text.DecimalFormat

class DecrincreControllerImpl(
        private val callback: (String)-> Unit
): DecrincreController,
        ValueAnimator.AnimatorUpdateListener {

    companion object{
        private const val DURATION = 1000L
    }

    private var _interpolator: Interpolator = DecelerateInterpolator()
    private var _durationAlgorithm: DurationAlgorithm by readWrite { DurationAlgorithm.default }
    private var _decimalFormatter: DecimalFormat?= null
    private val _animator by lazy {
        val animator = ValueAnimator()
        animator.addUpdateListener(this)
        animator
    }

    private var number: Number?= null

    fun exportAttributes(typedArrayAttributes: TypedArray){
        parseInterpolatorValue(
                typedArrayAttributes.getInteger(R.styleable.DecrincreView_decrincre_interpolator, 0)
        )

        setDecimalFormatter(
                DecimalFormat(
                        typedArrayAttributes.getString(R.styleable.DecrincreView_decrincre_decimalFormat) ?: "0.00"
                )
        )

        val numberTypedValue = TypedValue()
        typedArrayAttributes.getValue(R.styleable.DecrincreView_decrincre_number, numberTypedValue)

        val number: Number? = when (numberTypedValue.type) {
            TypedValue.TYPE_FLOAT -> {
                numberTypedValue.float
            }
            TypedValue.TYPE_FIRST_INT -> numberTypedValue.data
            else -> null
        }

        setNumber(number, false)
    }

    override fun setNumber(number: Number?, withAnimation: Boolean) {
        if(
                number == null ||
                withAnimation.not()
        ){
            changeText(number)
            return
        }

        startCountAnimation(
                (this.number ?: 0),
                number
        )
    }

    override fun setInterpolator(interpolator: Interpolator) {
        this._interpolator = interpolator
    }

    override fun getInterpolator() = _interpolator

    override fun setDecimalFormatter(decimalFormatter: DecimalFormat) {
        this._decimalFormatter = decimalFormatter
    }

    override fun getDecimalFormatter() = _decimalFormatter

    override fun setDurationAlgorithm(algorithm: DurationAlgorithm) {
        _durationAlgorithm = algorithm
    }

    override fun getDurationAlgorithm() = _durationAlgorithm

    override fun reset() {
    }

    private fun startCountAnimation(previousNumber: Number, newNumber: Number){
        setAnimatorConfig(previousNumber, newNumber)

        if(previousNumber != newNumber) {
            _animator.cancel()
            _animator.start()
        }else
            changeText(newNumber)

    }

    private fun setAnimatorConfig(previousNumber: Number, newNumber: Number){
        val duration = _durationAlgorithm.calculate(DURATION, previousNumber, newNumber)
        _animator.duration = duration
        _animator.interpolator = _interpolator
        _animator.setValuesOfNumbers(previousNumber, newNumber)
    }


    override fun onAnimationUpdate(animation: ValueAnimator?) {
        animation ?: return
        changeText(
                animation.animatedValue as Number
        )
    }

    private fun parseInterpolatorValue(value: Int){
        when(value){
            1-> _interpolator = DecelerateInterpolator()
            2-> _interpolator = AccelerateInterpolator()
            3-> _interpolator = AccelerateDecelerateInterpolator()
        }
    }


    private fun changeText(number: Number?){
        this.number = number
        callback.invoke(string(number))
    }

    private fun string(number: Number?): String{
        if(number == null) return ""

        return if(number is Int)
            number.toString()
        else
            _decimalFormatter?.format(number) ?: ""
    }
}