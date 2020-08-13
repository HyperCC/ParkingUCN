package cl.ucn.disc.pdis.appparkingucn;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cl.ucn.disc.pdis.appparkingucn.fragment.BuscarInicio;


public class Buscar extends AppCompatActivity {

    Fragment fragmentInicioBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        fragmentInicioBuscar = new BuscarInicio();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, fragmentInicioBuscar).commit();
    }
}
