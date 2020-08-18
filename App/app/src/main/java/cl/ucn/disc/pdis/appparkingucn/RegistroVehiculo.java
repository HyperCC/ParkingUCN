package cl.ucn.disc.pdis.appparkingucn;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cl.ucn.disc.pdis.appparkingucn.fragment.*;

public class RegistroVehiculo extends AppCompatActivity {

    Fragment fragmentInicioRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vehiculo);

        fragmentInicioRegistro = new RegistrarInicio();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, fragmentInicioRegistro).commit();
    }
}
