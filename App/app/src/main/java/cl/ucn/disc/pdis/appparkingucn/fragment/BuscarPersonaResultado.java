package cl.ucn.disc.pdis.appparkingucn.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cl.ucn.disc.pdis.appparkingucn.Buscar;
import cl.ucn.disc.pdis.appparkingucn.R;
import cl.ucn.disc.pdis.simplescraper.zeroice.model.Persona;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarPersonaResultado extends Fragment {

    Persona persona;
    TextView rutPersona;
    TextView nombrePersona;

    View vista;


    /**
     *  Fragment constructor
     */
    public BuscarPersonaResultado() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TODO: Hacer elementos del fragmento funcionales

        //TODO: Hacer funcionales las transacciones de fragmentos

        vista = inflater.inflate(R.layout.fragment_buscar_persona_resultado, container, false);

        rutPersona = (TextView) vista.findViewById(R.id.rutPersonaBuscada);
        nombrePersona = (TextView) vista.findViewById(R.id.nombrePersonaBuscada);

        persona = ((Buscar)getActivity()).getRegistro();

        rutPersona.setText(persona.rut);
        nombrePersona.setText(persona.nombre);

        // Inflate the layout for this fragment
        return vista;
    }
}