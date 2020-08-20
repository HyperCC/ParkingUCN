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

import cl.ucn.disc.pdis.appparkingucn.R;
import cl.ucn.disc.pdis.appparkingucn.RegistroVehiculo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {RegistrarInicio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarInicio extends Fragment {

    Button botonRegistrar;
    RadioGroup tipoRegistro;
    EditText editText;
    View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_registrar_inicio, container, false);

        botonRegistrar = (Button) vista.findViewById(R.id.botonRegistrar);
        tipoRegistro = (RadioGroup) vista.findViewById(R.id.tipoRegistro);
        editText = (EditText) vista.findViewById(R.id.textInputEditText);


        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipoRegistro.getCheckedRadioButtonId() == R.id.entrada) {

                    ((RegistroVehiculo)getActivity()).limpiarPatente(editText.getText().toString());
                }
                if (tipoRegistro.getCheckedRadioButtonId() == R.id.salida) {
                    //TODO: metodo salida
                    Toast.makeText(getContext(), "El valor seleccionado es: "+ tipoRegistro.getCheckedRadioButtonId(), Toast.LENGTH_SHORT).show();
                }
                //TODO: limpiar datos ingresados
            }
        });
        return vista;
    }
}