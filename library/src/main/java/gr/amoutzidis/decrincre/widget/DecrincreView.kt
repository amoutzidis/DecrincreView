package gr.amoutzidis.decrincre.widget

import android.content.Context
import android.util.AttributeSet
import android.view.animation.Interpolator
import androidx.appcompat.widget.AppCompatTextView
import gr.amoutzidis.decrincre.R
import gr.amoutzidis.decrincre.algorithms.DurationAlgorithm
import gr.amoutzidis.decrincre.controllers.DecrincreController
import gr.amoutzidis.decrincre.controllers.DecrincreControllerImpl
import java.text.DecimalFormat


class DecrincreView: AppCompatTextView,
    DecrincreController {

    constructor(context: Context) : super(context){ initialize(null) }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){ initialize(attrs) }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){ initialize(attrs) }

    private val controller =
        DecrincreControllerImpl {
            text = it
        }

    private fun initialize(attrs: AttributeSet?){
        exportAttributes(attrs)
    }

    private fun exportAttributes(attrs: AttributeSet?){
        val mutableAttributes = attrs ?: return
        val attributes =
            context.obtainStyledAttributes(mutableAttributes, R.styleable.DecrincreView)

        controller.exportAttributes(attributes)
    }

    override fun setNumber(number: Number?, withAnimation: Boolean) {
        controller.setNumber(number, withAnimation)
    }

    override fun setInterpolator(interpolator: Interpolator) {
        controller.setInterpolator(interpolator)
    }

    override fun getInterpolator(): Interpolator {
        return controller.getInterpolator()
    }

    override fun setDecimalFormatter(decimalFormatter: DecimalFormat) {
        controller.setDecimalFormatter(decimalFormatter)
    }

    override fun getDecimalFormatter(): DecimalFormat? {
        return controller.getDecimalFormatter()
    }

    override fun setDurationAlgorithm(algorithm: DurationAlgorithm) {
        controller.setDurationAlgorithm(algorithm)
    }

    override fun getDurationAlgorithm(): DurationAlgorithm {
        return controller.getDurationAlgorithm()
    }

    override fun reset() {
    }

}