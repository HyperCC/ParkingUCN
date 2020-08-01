package cl.ucn.disc.pdis.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.ui.core.setContent
import androidx.ui.foundation.Text

class RegistroVehiculo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_vehiculo)
        val botonRegistrar = findViewById<Button>(R.id.botonRegistrar)
        val entradaSalida = findViewById<RadioGroup>(R.id.tipoRegistro)

        botonRegistrar.setOnClickListener {

            if(entradaSalida.checkedRadioButtonId==R.id.entrada){
                //TODO: metodo ingreso
                Toast.makeText(this, "El valor seleccionado es: "+entradaSalida.checkedRadioButtonId, Toast.LENGTH_SHORT).show()
            }

            if(entradaSalida.checkedRadioButtonId==R.id.salida){
                //TODO: metodo salida
                Toast.makeText(this, "El valor seleccionado es: "+entradaSalida.checkedRadioButtonId, Toast.LENGTH_SHORT).show()
            }

            //TODO: limpiar datos ingresados
        }
    }
}