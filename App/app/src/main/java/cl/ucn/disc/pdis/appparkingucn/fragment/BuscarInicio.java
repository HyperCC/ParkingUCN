package cl.ucn.disc.pdis.appparkingucn.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import cl.ucn.disc.pdis.appparkingucn.Buscar;
import cl.ucn.disc.pdis.appparkingucn.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarInicio extends Fragment {

    Button botonBuscar;
    RadioGroup seleccion;
    EditText editText;

    View vista;
    /**
     *  Fragment constructor
     */
    public BuscarInicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_buscar_inicio, container, false);

        botonBuscar = (Button) vista.findViewById(R.id.botonBuscar);
        seleccion = (RadioGroup) vista.findViewById(R.id.seleccion);
        editText = (EditText) vista.findViewById(R.id.editText);


        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seleccion.getCheckedRadioButtonId() == R.id.botonRut) {

                    ((Buscar)getActivity()).reestructurarRut(editText.getText().toString());
                }
                if (seleccion.getCheckedRadioButtonId() == R.id.botonPatente) {

                    //TODO: metodo salida
                    ((Buscar)getActivity()).resultadoVehiculo();
                    Toast.makeText(getContext(), "El valor seleccionado es: "+ seleccion.getCheckedRadioButtonId(), Toast.LENGTH_SHORT).show();
                }
                //TODO: limpiar datos ingresados
            }
        });
        return vista;
    }
}