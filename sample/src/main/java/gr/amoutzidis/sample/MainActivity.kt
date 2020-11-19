package gr.amoutzidis.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import gr.amoutzidis.decrincre.widget.DecrincreView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.numberRadioGroup)
        findViewById<Button>(R.id.decricreButton).setOnClickListener {
            decreaseIncrease()
        }
    }

    fun decreaseIncrease() {
        val id = radioGroup.checkedRadioButtonId
        Log.e("{{", "$id")
        when(id){
            R.id.integerRadio-> int()
            else-> float()
        }
    }

    private fun float(){
        findViewById<DecrincreView>(R.id.textView).setNumber( Random.nextDouble(-1000.0, 2000.0))
    }

    private fun int(){
        findViewById<DecrincreView>(R.id.textView).setNumber(Random.nextInt(-1000, 2000))
    }
}