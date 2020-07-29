package cl.ucn.disc.pdis.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.ui.core.setContent
import androidx.ui.foundation.Text

class RegistroVehiculo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_vehiculo)
        setContent {
            Text(text = "Modulo Registro")
        }
    }
}