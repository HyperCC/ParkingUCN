package cl.ucn.disc.pdis.appparkingucn;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class RegistroVehiculo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vehiculo);
        Button botonRegistrar = findViewById(R.id.botonRegistrar);
        RadioGroup entradaSalida = findViewById(R.id.tipoRegistro);

        botonRegistrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (entradaSalida.getCheckedRadioButtonId() == R.id.botonRut) {
                    //TODO: metodo ingreso
                    Toast.makeText(RegistroVehiculo.this, "El valor seleccionado es: "+ entradaSalida.getCheckedRadioButtonId(), Toast.LENGTH_SHORT).show();
                }
                if (entradaSalida.getCheckedRadioButtonId() == R.id.botonPatente) {
                    //TODO: buscar patente
                    Toast.makeText(RegistroVehiculo.this, "El valor seleccionado es: "+ entradaSalida.getCheckedRadioButtonId(), Toast.LENGTH_SHORT).show();
                }
                //TODO: limpiar datos ingresados
            }
        });
    }
}
