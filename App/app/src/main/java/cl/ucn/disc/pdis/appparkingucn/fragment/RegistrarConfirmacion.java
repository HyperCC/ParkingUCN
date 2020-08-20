package cl.ucn.disc.pdis.appparkingucn.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.ucn.disc.pdis.appparkingucn.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {RegistrarConfirmacion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarConfirmacion extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrar_confirmacion, container, false);
    }
}