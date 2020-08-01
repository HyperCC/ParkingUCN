package cl.ucn.disc.pdis.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class Buscar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)
        val botonBuscar = findViewById<Button>(R.id.botoncito)

        val seleccion = findViewById<RadioGroup>(R.id.seleccion)

        botonBuscar.setOnClickListener {

                if (seleccion.checkedRadioButtonId==R.id.botonRut)
                    //TODO: buscar rut
                    Toast.makeText(this, "El valor seleccionado es: "+seleccion.checkedRadioButtonId, Toast.LENGTH_SHORT).show()

                if (seleccion.checkedRadioButtonId==R.id.botonPatente)
                    //TODO: buscar patente
                    Toast.makeText(this, "El valor seleccionado es: "+seleccion.checkedRadioButtonId, Toast.LENGTH_SHORT).show()
        }
    }
}