package cl.ucn.disc.pdis.appparkingucn;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Buscar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        Button botonBuscar = findViewById(R.id.botonBuscar);
        RadioGroup seleccion = findViewById(R.id.seleccion);

       botonBuscar.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (seleccion.getCheckedRadioButtonId() == R.id.botonRut) {
                   //TODO: buscar rut
                   Toast.makeText(Buscar.this, "El valor seleccionado es: "+ seleccion.getCheckedRadioButtonId(), Toast.LENGTH_SHORT).show();
               }
               if (seleccion.getCheckedRadioButtonId() == R.id.botonPatente) {
                   //TODO: buscar patente
                   Toast.makeText(Buscar.this, "El valor seleccionado es: "+ seleccion.getCheckedRadioButtonId(), Toast.LENGTH_SHORT).show();


               }
           }
       });







    }
}
