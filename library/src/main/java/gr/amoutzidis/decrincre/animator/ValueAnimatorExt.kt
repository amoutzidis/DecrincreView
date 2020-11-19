package gr.amoutzidis.decrincre.animator

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator


fun ValueAnimator.setValuesOfNumbers(valueFrom: Number, valueTo: Number){

    if(valueTo is Int) {
        setValues(PropertyValuesHolder.ofInt("", valueFrom.toInt(), valueTo.toInt()))
    }else {
        setValues(PropertyValuesHolder.ofFloat("", valueFrom.toFloat(), valueTo.toFloat()))
    }

}