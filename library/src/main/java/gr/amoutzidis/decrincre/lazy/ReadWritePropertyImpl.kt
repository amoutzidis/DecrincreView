package gr.amoutzidis.decrincre.lazy

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ReadWritePropertyImpl<T>(private val block: ()->T): ReadWriteProperty<Any?, T>{

    private var cached: T?= null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if(cached != null)
            return cached!!

        cached = block.invoke()
        return cached!!
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        cached = value
    }

}

fun <T> readWrite(block: ()->T) =
    ReadWritePropertyImpl(block)