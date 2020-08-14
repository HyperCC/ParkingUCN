package cl.ucn.disc.pdis.appparkingucn.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.ucn.disc.pdis.appparkingucn.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarInicio extends Fragment {

    /**
     *  Fragment constructor
     */
    public BuscarInicio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TODO: Hacer elementos del fragmento funcionales

	    //TODO: Hacer funcionales las transacciones de fragmentos

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buscar_inicio, container, false);
    }
}