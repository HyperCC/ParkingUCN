package cl.ucn.disc.pdis.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.material.Button

class Buscar : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        setContent {
            Text(text = "Modulo Buscar")
        }
    }
}