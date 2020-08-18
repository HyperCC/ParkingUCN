package cl.ucn.disc.pdis.appparkingucn;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cl.ucn.disc.pdis.appparkingucn.fragment.*;


public class Buscar extends AppCompatActivity {

    Fragment fragmentInicioBuscar;
    Fragment fragmentPersonaResultado;
    Fragment fragmentVehiculoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        fragmentInicioBuscar = new BuscarInicio();
        fragmentPersonaResultado = new BuscarPersonaResultado();
        fragmentVehiculoResultado = new BuscarVehiculoResultado();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, fragmentInicioBuscar).commit();

    }

    public void resultadoPersona() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorFragments, fragmentPersonaResultado).addToBackStack(null).commit();
    }

    public void resultadoVehiculo() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorFragments, fragmentVehiculoResultado).addToBackStack(null).commit();
    }
}