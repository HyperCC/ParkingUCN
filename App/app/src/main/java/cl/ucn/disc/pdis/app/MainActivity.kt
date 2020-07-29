package cl.ucn.disc.pdis.app

import android.content.Intent
import cl.ucn.disc.pdis.app.Buscar
import cl.ucn.disc.pdis.app.RegistroVehiculo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.material.Button
import androidx.ui.tooling.preview.Preview
import cl.ucn.disc.pdis.app.ui.AppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           App()
        }
    }

    @Preview
    @Composable
    private fun App() {
        Column() {
           Text(text = "Parking APP UCN")

            Button(onClick = {startActivity(Intent(this@MainActivity, Buscar::class.java))}) {
                Text(text = "Buscar")
            }

            Button(onClick = {startActivity(Intent(this@MainActivity, RegistroVehiculo::class.java))}) {
                Text(text = "Registro")
            }



        }
    }
}

