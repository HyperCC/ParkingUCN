package cl.ucn.disc.pdis.appparkingucn;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import cl.ucn.disc.pdis.appparkingucn.fragment.BuscarInicio;
import cl.ucn.disc.pdis.appparkingucn.fragment.BuscarPersonaResultado;
import cl.ucn.disc.pdis.appparkingucn.fragment.BuscarVehiculoResultado;


public class Buscar extends AppCompatActivity {

    FragmentTransaction transaction;

    Fragment fragmentInicioBuscar;
    Fragment fragmentBuscarPersonaResultado;
    Fragment fragmentBuscarVehiculoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        fragmentInicioBuscar = new BuscarInicio();
        fragmentBuscarPersonaResultado = new BuscarPersonaResultado();
        fragmentBuscarVehiculoResultado = new BuscarVehiculoResultado();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments,fragmentInicioBuscar).commit();
    }
}
