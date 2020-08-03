package cl.ucn.disc.pdis.app

import android.content.Intent
import cl.ucn.disc.pdis.app.Buscar
import cl.ucn.disc.pdis.app.RegistroVehiculo
import com.zeroc.Ice.*
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.tooling.preview.Preview
import cl.ucn.disc.pdis.app.ui.AppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val busquedaMain = findViewById<Button>(R.id.Buscar)
        val registrarMain = findViewById<Button>(R.id.Registrar)
        setUiTransition(busquedaMain, registrarMain)
    }

    private fun setUiTransition(busquedaMain: Button, registrarMain: Button){

        busquedaMain.setOnClickListener {
            startActivity(Intent(this, Buscar::class.java))
        }

        registrarMain.setOnClickListener {
            startActivity(Intent(this, RegistroVehiculo::class.java))
        }
    }
}

